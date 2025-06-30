import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAllMethodInExecutorFramework {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // There is an another method called invokeAll() --> It takes multiple Callables & execute them all in a single go & stores all the future results in a list

        Callable<Integer> callable1 = () -> {
            System.out.println("Task 1");
            return 1;
        };

        Callable<Integer> callable2 = () -> {
            System.out.println("Task 2");
            return 2;
        };

        Callable<Integer> callable3 = () -> {
            System.out.println("Task 3");
            return 3;
        };

        List<Callable<Integer>> callableList = Arrays.asList(callable1, callable2, callable3);
        // It is a list that contains different Callables which returns an different Integer values. 

        List<Future<Integer>> futureValues = executor.invokeAll(callableList); // This method will execute all the Callables in on single go & stores all the results in a List that contains Integer number as a future value.

        for(Future<Integer> number : futureValues){
            System.out.println(number.get()); 
            // Get the future values
        }


        // There is an another method called "invokeAny" that takes a List of Callables & give the result of that Callable that finished it's execution first.
        
        int i = executor.invokeAny(callableList);
        System.out.println("Completed its execution 1st & the result is: " +i);

        executor.shutdown();


    }
}
