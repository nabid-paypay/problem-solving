package thread_prac;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTest {

    public static int func() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("OK");
        return 4;
        //DataProcessingServiceImpl
        //public class Bootup implements ApplicationRunner, DisposableBean {
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i=0; i<100; i++){
            executorService.submit(ExecutorTest::func);
        }
//        Future<Integer> future1 = executorService.submit(()-> {
//                    Thread.sleep(3000);
//                    //System.out.println("OK1");
//                    return 4;
//                });
//        Future<Integer> future2 = executorService.submit(()-> {
//                    Thread.sleep(5000);
//                    //System.out.println("OK2");
//                    return 4;
//        });
//
//        System.out.println("result1 = " + future1.get());
//        System.out.println("result1 = " + future2.get());

        executorService.shutdown();

    }
}
