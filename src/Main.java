import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<Integer> bq=new LinkedBlockingQueue<Integer>();
        final boolean[] add = {true};
        final int[] cnt = {0};
        Thread t1=new Thread(){
            public void run(){
                synchronized (bq){
                    while(!add[0]){
                        try{
                            wait();
                        }catch (InterruptedException e){
                            throw new RuntimeException(e);
                        }
                    }
                    add[0]=false;
                    for(int i=0; i<10000; i++){
                        cnt[0]++;
                        bq.add(cnt[0]);
                    }
                    notifyAll();
                }
            }
        };
        Thread t2=new Thread(){
            public void run(){
                synchronized (bq){
                    while(add[0]){
                        try{
                            wait();
                        }catch (InterruptedException e){
                            throw new RuntimeException(e);
                        }
                    }
                    add[0]=true;
                    for(int i=0; i<5000; i++){
                        bq.remove();
                    }
                    notifyAll();
                }
            }
        };
//        AddNumberToQueue atq=new AddNumberToQueue(bq);
//        RemoveNumberFromQueue rfq=new RemoveNumberFromQueue(bq);
//        Thread t1=new Thread(atq);
//        Thread t2=new Thread(rfq);
        t1.start();
        t2.start();
        while(t1.isAlive()){
            System.out.println("Thread 1 "+t1.getState());
        }
        System.out.println(bq);
        while(t2.isAlive()){
            System.out.println("Thread 2 "+t2.getState());
        }
        System.out.println(bq);
    }
}