class Pen{

    public synchronized void writeWithPenAndPaper(Paper paperObj){
        System.out.println(Thread.currentThread().getName() +" is using Pen " +this +" & trying to write on the Paper "+paperObj);

    }

    public synchronized void finishedWriting(){
        System.out.println(Thread.currentThread().getName() +" finished using the Pen " +this);
    }

}

class Paper{

    public synchronized void writeWithPaperAndPen(Pen penObj){
        System.out.println(Thread.currentThread().getName() +" is using paper " +this +" & trying to use the Pen "+penObj);

    }

    public synchronized void finishedWriting(){
        System.out.println(Thread.currentThread().getName() +" finished using the Pen " +this);
    }

}

class Task1 implements Runnable{

    private Pen penObj;

    private Paper paperObj;

    Task1(Pen penObj, Paper paperObj){
        this.penObj = penObj;
        this.paperObj = paperObj;
    }

    @Override
    public void run(){
        penObj.writeWithPenAndPaper(paperObj);  // Pen is trying to aquire the paper & write on it
    }

}

class Task2 implements Runnable{

    private Pen penObj;

    private Paper paperObj;

    Task2(Pen penObj, Paper paperObj){
        this.penObj = penObj;
        this.paperObj = paperObj;
    }

    @Override
    public void run(){
        synchronized(penObj){
            // Basically here we are saying that 1st of all aquire the lock for Pen & then request paper. It will ensure that thread-2 will aquire the lock  on the paper when the lock of the pen is present.
            paperObj.writeWithPaperAndPen(penObj);
        }
        // paperObj.writeWithPaperAndPen(penObj);  // Paper is trying to aquire the pen & wants to use the pen
    }

}

// Here deadlock occurs because both Pen & Paper wants to use each other at the same time as a result deadlock occurs.
// Therefore to ensure that deadlock not happens, we synchronized a block of code above like this.

public class DeadLockInJava {
    public static void main(String[] args) {
        
        Pen penObj = new Pen();

        Paper paperObj = new Paper();


        Task1 task1 = new Task1(penObj, paperObj);
        Task2 task2 = new Task2(penObj, paperObj);

        Thread t1 = new Thread(task1, "Thread-1");
        Thread t2 = new Thread(task2, "Thread-2");

        t1.start();
        t2.start();

    }
}
