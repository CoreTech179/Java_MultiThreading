import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class DependentService implements Runnable{

    private final CyclicBarrier barrierObj;

    DependentService(CyclicBarrier barrierObj){
        this.barrierObj = barrierObj;
    }

    @Override
    public void run(){
        try{

            System.out.println(Thread.currentThread().getName() +" started the service!");
            Thread.sleep(2000); // Wait for 2 sec 
            try {
                barrierObj.await();
                // Basically Barrier.await() method is used against an every indivisual thread & it will make that indivisual thread to wait at the barrier (Entrance Gate) until all the threads are came at the barrier.
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } 

        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("All The Services are Started Now since all the services have successfully reached the entrance gate!");
    }

}

/*
 * Explanation of Cyclic Barrier
 * 
 * --> Suppose we have 4 friends & all the 4 friends wanted to see a movie & let us assume 2 friends have arrived & another 2 is on the way therefore we both 2 friends will wait at the entrance gate until all the 4 friends are not arrived & as soon as all the 4 friends are arrived we will entre to see the movie. In this way the Cyclic Barrier exactly works.
 * 
 * Entrance Gate = barrier.await() method
 * 
 * --> Cyclic Barrier will not block the main Thread.
 */

public class CyclicBarrierInJava {
    public static void main(String[] args) {
        
        int numberOfServices = 3;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfServices);
        
        CyclicBarrier barrier = new CyclicBarrier(numberOfServices);

        executor.submit(new DependentService(barrier)); 
        executor.submit(new DependentService(barrier)); 
        executor.submit(new DependentService(barrier));

        System.out.println("Main is starting....");

        executor.shutdown();
    }
}
