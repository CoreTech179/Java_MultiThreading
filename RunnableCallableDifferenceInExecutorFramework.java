import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableCallableDifferenceInExecutorFramework {
    public static void main(String[] args) throws InterruptedException {
        

        // When we want to return anything then we use callable & when we don't want to return anything then we use Runnable.

        Callable<Integer> callable = () -> {
            return 1 + 2;
            // Here it returns an integer value.
            // We can return anything such as string, boolean,..etc.
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);

        /*
         * Basically we have 3 submit methods 
         * (i) That takes Runnable method
         * (ii) That takes Callable method
         * (iii) Submit method with Runnable & result as a parameter 
         */

        Future<Integer> submit = executor.submit(callable); 
        // Future means what we will get in future from Callable as a returned value.

        Future<?> submit2 = executor.submit(() -> System.out.println("Hello"));
        // This submit2 contains the status only 

        Future<String> submit3 = executor.submit(() -> System.out.println("Good Morning"), "Submit method worked properly with Runnable");
        // Here if the submit method worked successfully then the submit3 future value will contain this statement as a result value (i.e.Submit method worked properly with Runnable)
    
        try{

            System.out.println(submit.get()); // Since Callable is returning an Integer number therefore we will get an Integer value from get method

            System.out.println(submit2.get());  // Since Runnable doesn't return anything therefore there is no use of get method here.      

            System.out.println(submit3.get());

        }catch(Exception e){
            e.printStackTrace();
        }

        executor.shutdown();
        Thread.sleep(1000);
        System.out.println(executor.isTerminated());

    }
}
