package com.example.zafkiel;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.example.zafkiel.Entity.Centime;
import com.example.zafkiel.Entity.DataChangeReceiver;

public class Timeservice extends Service {
    static public String str1,str2;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {     //this is the function that continuously runs in this service
        Log.i("jabot", "后台进程。。。");
        Bundle bundle= intent.getExtras();
        Centime ct= (Centime) bundle.getSerializable("time_mes");
        DataChangeReceiver receiver=new DataChangeReceiver();//创建接收器, it can reflect when the clock turns over
        receiver.getc_t(ct);     //send centime to the receiver
        registerReceiver(receiver,new IntentFilter(Intent.ACTION_TIME_TICK));
        return super.onStartCommand(intent, flags, startId);

    }
    @Override
    public void onCreate() {

        super.onCreate();
        Log.i("jabot", "后台进程被创建。。。");

    }



    @Override
    public void onDestroy() {

        Log.i("jabot", "后台进程被销毁了。。。");
        super.onDestroy();
    }

}

