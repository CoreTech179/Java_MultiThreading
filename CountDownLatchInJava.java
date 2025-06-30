import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class DependentService implements Runnable{

    private final CountDownLatch latch;

    DependentService(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run(){
        try{

            System.out.println(Thread.currentThread().getName() +" started the service!");
            Thread.sleep(2000); // Wait for 2 sec 
            latch.countDown(); // Then decrease the value by 1

        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

}

public class CountDownLatchInJava {
    public static void main(String[] args) throws InterruptedException {
        
        // Basically we use CountDownLatch when we want to wait for the multiple Threads to finish its execution.
        // When we can use this ? --> Suppose we want to initialiing something before the main thread starts. 

        int numberOfServices = 3; // How many task we need to perform

        ExecutorService executor = Executors.newFixedThreadPool(numberOfServices); // Created Pool of Threads

        CountDownLatch latch = new CountDownLatch(numberOfServices); // Count value is now 3

        executor.submit(new DependentService(latch)); // Count down = 3
        executor.submit(new DependentService(latch)); // Count down = 2
        executor.submit(new DependentService(latch)); // Count down = 1

        // Finally CountDown = 0
        latch.await();
        
        System.out.println("Main is starting...."); // Main method is waiting until all the above task is completed

        executor.shutdown();

    }
}
