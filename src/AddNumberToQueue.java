import java.util.concurrent.BlockingQueue;

public class AddNumberToQueue implements Runnable{
    private BlockingQueue<Integer> queue;
    private int cnt=0;
    public AddNumberToQueue(BlockingQueue<Integer> q){
        this.queue=q;
    }
    public void run(){

        synchronized(this.queue){
            for(int i=0; i<10000; i++){
                cnt++;
                this.queue.add(cnt);
            }
        }
//        synchronized(new Object()){
//            try{
//                Thread.sleep(100);
//            }catch (InterruptedException e){
//                throw new RuntimeException(e);
//            }
//        }
//        System.out.println("Initial queue with added numbers: "+this.queue);
    }
}
