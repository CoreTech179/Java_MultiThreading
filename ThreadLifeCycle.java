public class ThreadLifeCycle extends Thread{

    @Override
    public void run(){
        System.out.println("Hello");

        try{

            Thread.sleep(3000); // Pause t1 for 3 sec to print the state of t1 

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        
        ThreadLifeCycle t1 = new ThreadLifeCycle(); // State = New --> Thread is created but not yet started

        // To check the state of a Thread we call an in-built method
        System.out.println(t1.getState());

        t1.start(); // State = Runnable --> It is ready to run & waiting for the CPU time

        System.out.println(t1.getState());

        // To pause the execution of main thread for certain amount time such that t1 thread should get some time to execute the task
        Thread.sleep(100);

        System.out.println(t1.getState()); // State of t1 thread after pausing for 3 sec = Timed_waiting & then terminated

        // Another in-built method is there i.e. join()

        t1.join(); // Basically by using this join() method inside the main function, the main thread will wait for for t1 to execute completely.
        
        System.out.println(t1.getState()); // State = terminated --> Because the main method waited for the t1 thread to execute completely.

    }
}
