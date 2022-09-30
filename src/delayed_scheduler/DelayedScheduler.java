package delayed_scheduler;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Future;

public class DelayedScheduler {
    private final DelayQueue<DelayTask> delayQueue;

    public DelayedScheduler() {
        this.delayQueue = new DelayQueue<>();
        this.startExecute();
    }

    public Future schedule(Runnable task, long delay){
        DelayTask delayTask = new DelayTask(task, delay);
        this.delayQueue.add(delayTask);
        return delayTask;
    }

    private void startExecute(){
        Runnable runnable = () -> {
            while (true) {
                try {
                    DelayTask task = this.delayQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable, "Executor").start();
    }

    public static void main(String[] args) {
        DelayedScheduler delayedScheduler = new DelayedScheduler();
        Runnable runnable = () -> {
            System.out.println("OK");
        };
        delayedScheduler.schedule(runnable, 1000);
        delayedScheduler.schedule(runnable, 5000);
    }
}
