package thread_prac;

import java.util.LinkedList;
import java.util.List;

public class MyThreadPool implements ThreadPool {
    private volatile boolean running = true;
    private final List<Runnable> tasks = new LinkedList<>();

    public MyThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            var workerThread = new WorkerThread("worker-" + i);
            workerThread.start();
        }
    }
    private Runnable take() throws InterruptedException {
        synchronized (tasks) {
            while (tasks.isEmpty()) {
                tasks.wait();
            }
            return tasks.remove(0);
        }
    }
    @Override
    public void submit(Runnable unitOfWork) {
        synchronized (tasks) {
            tasks.add(unitOfWork);
            tasks.notifyAll();
        }
    }
    @Override
    public void shutdown() {
        this.running = false;
    }

    private class WorkerThread extends Thread {
        public WorkerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Runnable currentTask = take();
                    currentTask.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
