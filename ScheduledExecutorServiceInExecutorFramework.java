import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceInExecutorFramework {
    public static void main(String[] args) {
        

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // If we want to perform a task after some time then we can use scheduledExecutorService

        scheduler.schedule(
            () -> {
            System.out.println("This Task is executed & Terminated after 5 sec of delay!");
        }, 
        5, 
        // Here delay represents that after 5 sec the above statement written inside the lamda expression will execute & terminated.
        TimeUnit.SECONDS
        );


        // If we want to perform same task periodically (i.e. Suppose after every 4 sec we have to print "hello world" then we use an in-built method name scheduleAtFixedRate )

        scheduler.scheduleAtFixedRate(
            () -> {

                System.out.println("This Task is executing after every 4 sec of delay !");

            }, 4, 4, TimeUnit.SECONDS

            // Here initial delay means after 4 sec the task will start to execute & after every 4 sec it is executing the same task
            
        );

        scheduler.schedule(
            // Basically this schedule method will wait for 20 sec's to complete all the execution & then after 20 sec it will shutdown the scheduler.
            () -> {
                System.out.println("Shutdown Initiating .. ");
                scheduler.shutdown();

            }, 20, TimeUnit.SECONDS);
        
    }
}
