package thread_prac;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class main {
    private void test() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("awake");
    }
    public static void main(String[] args) throws InterruptedException {
//        var counter = new AtomicLong();
//        var t1 = new Thread(() -> {
//            for (int i = 0; i < 10000000; i++) {
//                counter.incrementAndGet();
//            }
//        });
//        var t2 = new Thread(() -> {
//            for (int i = 0; i < 1000; i++) {
//                counter.incrementAndGet();
//            }
//        });
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        long count = counter.get();
//        System.out.println("count = " + count);

        main main = new main();
        main.test();
    }
}
