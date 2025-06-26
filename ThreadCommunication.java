
// Here inside the SharedResource class the logic behind that, if the data is already present inside the buffer memory i.e. hasData = "true" already than why produce another data. Then we have to wait & notify the other thread to consume the data   

class SharedResource{
    private int data;
    private boolean hasData;

    public synchronized void produce(int value){

        while (hasData) {
            // if data is already present then wait, do not produce & store another data.
            try{

                wait();

            }catch(Exception e){
                Thread.currentThread().interrupt();
            }
        }

        data = value; // If data is not present than store the data 
        hasData = true; // then make hasData = true
        notify(); // And then notify the thread to consume the data.
        System.out.println("Produced: "+ value);
    }

    public synchronized int consume(){

        while (!hasData) {
            // if data is not present then we have to wait to produce next data.
            try{

                wait();

            }
            catch(Exception e){
                Thread.currentThread().interrupt();
            }
        }

        hasData = false; // If data is present then the data must consumed therefore we have to make hasData = false in prior
        notify(); // Then we have to notify that there is no more data, produce next data.
        System.out.println("Consumed: "+ data);
        return data; // Consumed the data by returning the data.

    }
}

class Producer implements Runnable{

    private SharedResource sharedResourceObj;

    Producer(SharedResource sharedResourceObj){
        this.sharedResourceObj = sharedResourceObj;
    }

    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            sharedResourceObj.produce(i);
            
        }
    }

}

class Consumer implements Runnable{

    private SharedResource sharedResourceObj;

    Consumer(SharedResource sharedResourceObj){
        this.sharedResourceObj = sharedResourceObj;
    }

    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            int value = sharedResourceObj.consume();
        }
    }

}


public class ThreadCommunication {
    public static void main(String[] args) {
        
        SharedResource sharedResourceObj = new SharedResource();

        Producer producerObj = new Producer(sharedResourceObj);
        Consumer consumerObj = new Consumer(sharedResourceObj);


        Thread producerThread = new Thread(producerObj, "Producer");
        Thread consumerThread = new Thread(consumerObj, "Consumer");

        producerThread.start();
        consumerThread.start();

    }
}
