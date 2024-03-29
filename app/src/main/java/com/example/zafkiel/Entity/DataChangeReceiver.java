package com.example.zafkiel.Entity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.zafkiel.mymusicplayer.MusicActivity;
import com.example.zafkiel.DialogActivity;
import com.example.zafkiel.NotifyActivity;


import java.util.Calendar;

public class DataChangeReceiver extends BroadcastReceiver {
    private String str_1, str_2,path,notes;
    private int position;
    @Override
    public void onReceive(Context context, Intent intent) {    //time-tick触发调用，后台每分钟一次接受，并调用下面的操作
        Log.i("jabot", "123321");
        Log.i("jabot", str_1+":"+str_2);
        Calendar mc=Calendar.getInstance();
        int hour = mc.get(Calendar.HOUR);
        int minute = mc.get(Calendar.MINUTE);
        String hr=String.valueOf(hour);
        String min=String.valueOf(minute);
        Intent intent1 = new Intent(context,DialogActivity.class);
        intent1.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.putExtra("notes",notes);
        Intent i = new Intent(context,MusicActivity.class);
        i.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("position",position);
        i.putExtra("musicPath",path);
        i.putExtra("notes",notes);

        if(hr.equals(str_1) && min.equals(str_2)) {
            Log.i("jabot", "到时间了");//.... 开始调用你们的功能
            context.startActivity(intent1);
            context.startActivity(i);
        }
    }

    public String getc_t(Centime ct)   //获取该类的字符串（时间）成员，可以改ct
    {
        str_1=ct.getHour();
        str_2=ct.getMin();
        path=ct.getPath();
        position=ct.getPositon();
        notes=ct.getHint();
        return  str_1+str_2;
    }

}

