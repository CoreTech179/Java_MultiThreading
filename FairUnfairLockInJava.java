import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class UnFairLock{
    private final Lock key = new ReentrantLock(true);

    public void accessResource(){
        key.lock();
        try{
            System.out.println(Thread.currentThread().getName() +" accessed the method & locked the method!");
            Thread.sleep(1000);
        }catch(Exception e){
            // If any exception occured then we will always interrupted the Lock
            Thread.currentThread().interrupt();

        }finally{
            key.unlock();
            System.out.println(Thread.currentThread().getName() +" unlocked the method!");
        }
    }
}

public class FairUnfairLockInJava {

    public static void main(String[] args) {
        
        UnFairLock unFairLockObj = new UnFairLock();

        Runnable runnable = new Runnable() {
            
            @Override
            public void run(){
                unFairLockObj.accessResource();
            }

        };

        Thread t1 = new Thread(runnable, "Thread-1");
        Thread t2 = new Thread(runnable, "Thread-2");
        Thread t3 = new Thread(runnable, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        // Here we are seeing that all the threads are accessing the method in a random manner while running the code but we want whichever thread comes first get access to the resource first.

        // Therefore we pass a boolean value "true" as a parameter to the constructor of the ReentrantLock.

        // By doing this if t1 locked the method first then next t2 will lock the method & then t3 in this way.

        // If t3 then next t2 & then t1

        // Here we also get an benefit that suppose there are multiple threads & by any chance there is 1 thread which is waiting infinitely & that thread is not getting any chance to execute this is known as starvation. As a result if we pass the boolean value as "true" therefore every indivisual thread will get an equal chance to execute. 

    }
}