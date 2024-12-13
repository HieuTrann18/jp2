package Entity;

public class ThreadOne extends Thread{
    private Data data;
    private ThreadTwo threadTwo;

    public ThreadOne(Data data,  ThreadTwo threadTwo){
        this.data = data;
        this.threadTwo = threadTwo;
    }

    @Override
    public void run(){
        for(String text : data.getData()){
            String textResult = text.toUpperCase();
            threadTwo.display(textResult);
        }
    }
}
