/*
 * Syntax to Create a Thread by extending the Thread class
 */
class World extends Thread{

    @Override
    public void run(){
        // run is a overridden method where we define our own code here
        while(true){
            // System.out.println("World class Thread");
            System.out.println(Thread.currentThread().getName());
        }
    }

}


/*
 * Syntax to Create a Thread by implementing the Runnable Interface
 */

 class Print implements Runnable{
    @Override
    public void run(){
        System.out.println("I am thread from Print class");
    }
 }

public class TestThread {
    public static void main(String[] args) {
        
        World worldObj = new World();

        // To start running a new thread we use the start() method for it

        worldObj.start(); // Now the thread from the World Class starts running 

        // Main Thread also starts running

        while (true) {
            // System.out.println("Main Thread"); 
            System.out.println(Thread.currentThread().getName()); // It prints the name of the thread that is currently running
        }

        /*
         * Since both the threads are running simultaneously, therefore in the output it prints the name of the threads randomly.
         */



        /*
         * Case 2: How to create a new thread of Print class that implements the Runnable interface
         */

        // 1. Create a object of Print class
        // 2. Create a object of Thread class
        // 3. Pass the object of the print class as a parameter to the Thread constructor
        // 4. with the help of the Thread object use the start method 
        
        
        /*
            Like this way

            Print printObj = new Print();
            Thread t1 = new Thread(printObj);
            t1.start();
            
         */

        


    }
}
