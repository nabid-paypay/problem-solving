package thread_prac;

public interface ThreadPool {

    void submit(Runnable unitOfWork);
    void shutdown();
}
