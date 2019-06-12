package com.example.zafkiel.Entity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.zafkiel.mymusicplayer.MusicActivity;

import java.util.Calendar;

public class DataChangeReceiver extends BroadcastReceiver {
    private String str_1, str_2,path;
    @Override
    public void onReceive(Context context, Intent intent) {    //time-tick触发调用，后台每分钟一次接受，并调用下面的操作
        Log.i("jabot", "123321");
        Log.i("jabot", str_1+":"+str_2);
        Calendar mc=Calendar.getInstance();
        int hour = mc.get(Calendar.HOUR);
        int minute = mc.get(Calendar.MINUTE);
        String hr=String.valueOf(hour);
        String min=String.valueOf(minute);

        if(hr.equals(str_1) && min.equals(str_2)) {
            Log.i("jabot", "到时间了");//.... 开始调用你们的功能

            intent.setClass(context.getApplicationContext(), MusicActivity.class);
            intent.putExtra("musicPath",path);
            context.startActivity(intent);
        }
    }

    public String getc_t(Centime ct)   //获取该类的字符串（时间）成员，可以改ct
    {
        str_1=ct.getHour();
        str_2=ct.getMin();
        path=ct.getPath();
        return  str_1+str_2;
    }

}

