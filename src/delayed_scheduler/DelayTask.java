package delayed_scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class DelayTask extends FutureTask implements Delayed {
    private final long startTime;
    private final Runnable task;

    public DelayTask(Runnable task, long delayTime) {
        super(task, null);
        this.task = task;
        this.startTime = System.currentTimeMillis() + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = this.startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), ((DelayTask) o).getDelay(TimeUnit.MILLISECONDS));
    }
}
