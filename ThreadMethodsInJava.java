public class ThreadMethodsInJava extends Thread{

    // Setting a name for our thread

    ThreadMethodsInJava(String name){
        super(name);
    }

    @Override
    public void run(){

        // 2nd method: To pause the execution for certain amount of time
        try{

            for(int i = 0; i < 1000000000; i++){

                Thread.sleep(1000); // Pause the execution for some amount of time

                System.out.println(Thread.currentThread().getName() + " -Priority --> " +Thread.currentThread().getPriority());

                Thread.yield(); // Here by this method we are giving a hint to the schedular to give a chance to another thread for it's execution. The schedular may ignore this.

            }

        }catch(Exception e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        
        // We can also set a name for our custom thread

        ThreadMethodsInJava t1 = new ThreadMethodsInJava("Thread-Hello");

        // 1st method: To start a thread

        t1.start();

        // With the help of the join method, the main method will wait for completing the execution of the t1 thread

        // t1.join(); 

        // We can also set priorities for all the Threads by using an in-buit method

        // Let's create another 2 threads

        ThreadMethodsInJava lowPriority = new ThreadMethodsInJava("Thread-world");
        ThreadMethodsInJava midPriority = new ThreadMethodsInJava("Thread-guru");

        t1.setPriority(Thread.MAX_PRIORITY);
        midPriority.setPriority(Thread.NORM_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        

        lowPriority.start();
        midPriority.start();


        // To Stop a thread we use an in-built method
        t1.interrupt();  // Here we are interrupting the t1 thread.


        // Q. What is a Deamon Thread ?
        // Deamon Thread is basically a background thread & the JVM will not wait for the Deamon thread to complete its execution rather it will stop.

        t1.setDaemon(true); // Here t1 is now acting as a background thread & when the JVM sees that other threads have already completed its execution then t1 also stops because it is unwanted.

    }
}
