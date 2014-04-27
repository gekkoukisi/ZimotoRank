package tokki.planningdev.com.zimotorank.activity;
public class Util{

    //指定ミリ秒実行を止めるメソッド
    public synchronized void sleep(long msec)
    {
        try
        {
            wait(msec);
        }catch(InterruptedException e){}
    }



}