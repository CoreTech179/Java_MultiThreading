class Counter{

    private int count = 0;

    public synchronized void increment(){
        // Declaring this method as synchronized such that 1 single thread should access this method at a time.
        // Benefit: The Count value will remain consistant & it will not give an error
        count++;
    }

    // Now if we want only a particular block of code should synchronized not the entire method then we do like this

    public void incrementValue(){
        synchronized(this){
            count++;
        }
    }

    public int getCount(){
        return count;
    }

}

class MyThread extends Thread{

    private Counter counterObj;

    MyThread(Counter counterObj){
        this.counterObj = counterObj;
    }


    @Override
    public void run(){
        for(int i = 0; i < 10000; i++){
            counterObj.increment();
        }
    }

}



public class SynchronizationInJava {
    public static void main(String[] args) {
        
        Counter obj = new Counter();

        // Both t1 & t2 Thread is sharing the same resource at a time.

        MyThread t1 = new MyThread(obj);

        MyThread t2 = new MyThread(obj);

        t1.start();
        t2.start();

        try{

            // Here we are waiting for t1 & t2 thread to fully complete it's execution & then we print the count 

            t1.join();
            t2.join();

        }catch(Exception e){
            System.out.println(e);
        }

        System.out.println(obj.getCount()); // Ideally what should we are expecting 10000 + 10000 --> count = 20000. But let's run the program.

        // Now we are observing that while running the program we are getting different values every time because both t1 & t2 thread is sharing the same resource (i.e. Counter Object). Technically this condition is called as race condition

        // Now if we want only a single thread will access the method at a time then we use the "synchonized keyword in Java"

        // As we have declared the "increment method" as synchronized therefore now it will give an accurate result (i.e. 20,000) as expected because we have overcome the race around condition.


    }
}
