import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecuterFrameworkInJava {

    public static int Factorial(int n){
        try {
            Thread.sleep(1000);
            // It will take a break of 1sec & then it will proceed to execute the rest of the code.
            // Here we have intensionally did Thread.sleep for 1sec to show that finding out the Factorial of a number requires high computational power.
            // And we see at the Output it is taking time 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        int result = 1;

        for(int i = 1; i <= n; i++){
            result *= i;
        }

        return result;
    }
    
    public static void main(String[] args) {
        
        /*
         * 
            // Let's see how executor framework works by taking a suitable example
            // Example is: We need to find the factorial from 1 to 10 & we are considering it requires high computational power
    
            // Let's check how much time it takes to execute 
            long startTime = System.currentTimeMillis();
    
            // Creating an array of Threads
    
            Thread [] threads = new Thread[9];
    
            for(int i = 1; i < 10; i++){
                // System.out.println(Factorial(i));
                // Let's create a thread
                int finalI = i;
                threads[i - 1] = new Thread(
                    () -> {
                        // This Lamda expression is the run method we are implementing on the fly because it is a functional interface in java
                        int result = Factorial(finalI);
                        System.out.println(result);
                    }
                );
    
                threads[i - 1].start();
            }
    
            for(Thread thread : threads){
                try{
                    thread.join();
                    // Waiting for all the threads to execute
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
    
            System.out.println("Total Time taken: "+ (System.currentTimeMillis() - startTime));

        */


        // Here we have observed it has taken approximately 10.11 sec's to execute the entire code which is a lot of time.
        

        // When we are executing the above code by creating a thread then the statement for the total time taken will execute 1st because it will show the time taken to create the thread not the time taken to execute the code because we haven't wait for the threads to finish its execution.

        // Suppose there are 9 threads & we want all the 9 threads should work together & provide me with the accurate result after completing the execution of all the 9 threads. Then we have to create an array of 9 Threads & also provide an statement to wait for all the threads to execute.

        // After completion of execution of all the threads then the total time statement will show us how much time is taken. 

        // Now we observed that it takes 1 sec to finish its execution. But as we know we have to create thread manually & have to sart manually & also we are unable to reuse these threads. Therefore Executor framework is introduced & it says that you only write the bussiness logic I will create the thread & run all the threads by myself.


        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(9); // Created 9 threads

        for(int i = 1; i < 10; i++){
                int finalI = i;

                // Written our bussiness logic inside the lamda expression & pass down as a parameter to an in-built function submit 
                executor.submit(
                    () -> {
                        // This Lamda expression is the run method we are implementing on the fly because it is a functional interface in java
                        int result = Factorial(finalI);
                        System.out.println(result);
                    }
                );
        }

        // Then we have to stop the executor to reuse the threads in future
        executor.shutdown();

        // If we want to wait for all the threads to complete it's execution fully & then print the below statement then we can acheive it by using an in-built function awaitTermination

        try {
            executor.awaitTermination(100, TimeUnit.SECONDS);
            // Wait for 100 sec to see if all the threads have completed it's execution or not
            // If completed then go to the print statement & execute 
            // else throw an exception
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total Time taken: "+ (System.currentTimeMillis() - startTime));

    }
}
