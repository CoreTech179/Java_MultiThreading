import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureMethodsInExecutorFramework {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> futureValue = executor.submit(
            () -> {

                try{
                    Thread.sleep(2000); // Waiting for 2 sec here & then it will return 42
                }catch(Exception e){
                    System.out.println(e);
                }
                
                return 42;
            }

        );
        /*
            futureValue.cancel(false); // This method will cancel the execution of the above task if the boolean value is true
        */
        System.out.println(futureValue.get()); // Print the returned value that is stored inside the futureValue
        System.out.println(futureValue.isDone()); // Here isDone method is used to check whether the task is completed successfully or not
        System.out.println(futureValue.isCancelled()); // Here is Cancelled method is used to check if the running task is interupted & cancelled or not 
        
        executor.shutdown();

    }
}
