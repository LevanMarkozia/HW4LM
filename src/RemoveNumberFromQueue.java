import java.util.concurrent.BlockingQueue;

public class RemoveNumberFromQueue implements Runnable {
    private BlockingQueue<Integer> queue;
    public RemoveNumberFromQueue(BlockingQueue<Integer> q){
        this.queue=q;
    }
    public void run(){
//        synchronized(new Object()){
//            try{
//                Thread.sleep(100);
//            }catch (InterruptedException e){
//                throw new RuntimeException(e);
//            }

//        }
        synchronized(this.queue){
            for(int i=0; i<5000; i++){
                this.queue.remove();
            }
        }

        System.out.println("Final queue after removing the numbers: "+this.queue);
    }
}
