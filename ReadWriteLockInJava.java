import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteCounter{

    private int count = 0;

    private final ReadWriteLock key = new ReentrantReadWriteLock();

    private final Lock readKey = key.readLock();

    private final Lock writeKey = key.writeLock();

    public void incrementValue(){
        writeKey.lock();
        try{

            count++;

        }catch(Exception e){

            Thread.currentThread().interrupt();

        }finally{

            writeKey.unlock();

        }
    }

    public int getCount(){

        readKey.lock();

        try{
            return count;

        }finally{
            readKey.unlock();
        }
        
    }
}


public class ReadWriteLockInJava {

    public static void main(String[] args) {

        ReadWriteCounter readWriteCounterObj = new ReadWriteCounter();

        Runnable readTask = new Runnable() {
            
            @Override
            public void run(){
                for(int i = 0; i < 10; i++){
                    System.out.println(Thread.currentThread().getName() +" read: " +readWriteCounterObj.getCount());
                }
            }

        };

        Runnable writeTask = new Runnable() {
            
            @Override
            public void run(){
                for(int i = 0; i < 10; i++){
                    readWriteCounterObj.incrementValue();
                    System.out.println(Thread.currentThread().getName() +" Incremented the Value");
                }
            }

        };


        Thread writerThread = new Thread(writeTask, "write-thread");
        Thread readerThread1 = new Thread(readTask, "reader-thread-1");
        Thread readerThread2 = new Thread(readTask, "reader-thread-2");

        writerThread.start();
        readerThread1.start();
        readerThread2.start();


        try{
            writerThread.join();
            readerThread1.join();
            readerThread2.join();

        }catch(Exception e){
            Thread.currentThread().interrupt();
        }

        System.out.println("Final count is = " +readWriteCounterObj.getCount());



        
    }
    
}
