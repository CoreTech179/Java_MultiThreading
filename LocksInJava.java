import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount extends Thread{

    private int balance = 100;

    private final Lock key = new ReentrantLock();

    public void withdraw(int amount){

        // In this code the problem is that when t1 is accessing this method then t2 will not access this method but t2 will wait for t1 to complete its execution & then t2 will access this method.

        // But suppose if some fault occurs i.e. Suppose t1 thread is taking longer time than expected, as a result t2 will also wait for longer time which should not be done.

        // If t1 Thread is taking longer time then t2 should not wait, t2 should come later. right!. Therefore we use the "Lock" interface to overcome this problem & remove the synchronized keyword from the method also. 


        /* System.out.println(Thread.currentThread().getName() +" is attempting to withdraw.... " +amount);

        if(balance >= amount){

            System.out.println(Thread.currentThread().getName() +" procedding to withdraw.. ");

            try{

                Thread.sleep(3000);

            }catch(Exception e){
                System.out.println(e);
            }

            balance -= amount;
            System.out.println(Thread.currentThread().getName() +" withdraw " +amount +" successful");

        }else{

            System.out.println("Insufficient balance...");

        }
            */

        System.out.println(Thread.currentThread().getName() +" is attempting to withdraw.... " +amount);

        try{
            if(key.tryLock(1000,TimeUnit.MILLISECONDS)){
                // tryLock() method will acquire the key & try to lock the method if it is available at the time of invocation immediately otherwise the another Thread will comeback later.

                // tryLock(time_In_Numbers, TimeUnit.MILISECONDS) method will allow the another thread to wait for the given amount of time (i.e. If the key is available after the given amount of time then I will take the key & lock the method & use this method otherwise I will comeback later)

                if(balance >= amount){
                    System.out.println(Thread.currentThread().getName() +" procedding to withdraw.. ");

                    try{

                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() +" withdraw " +amount +" successful");
                        System.out.println("Remaining Balance is = "+balance);

                    }catch(Exception e){
                        Thread.currentThread().interrupt();
                    }
                    finally{
                        key.unlock();
                    }

                }
                else{
                    System.out.println("Insufficient balance...");
                }

            }
            else{
                System.out.println(Thread.currentThread().getName()+" Unable to get the key & lock the method at the moment because another thread is already locked this method. I will try later!");
            }

        }catch(Exception e){
            Thread.currentThread().interrupt();
        }
    }
}

// Note: Everytime a method is locked, the value of count increases & everytime when the method is unlocked, the value of count decreases.
// The count variable is present inside it but we don't see it.


public class LocksInJava {
    public static void main(String[] args) {

        BankAccount bankAccountObj = new BankAccount();

        Runnable runnableObj = new Runnable() {
            
            @Override
            public void run(){
                bankAccountObj.withdraw(50);
            }

        };

        Thread t1 = new Thread(runnableObj, "Thread-1");

        Thread t2 = new Thread(runnableObj, "Thread-2");

        t1.start();
        t2.start();
        
    }
}
