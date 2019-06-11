package com.example.zafkiel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zafkiel.Entity.Centime;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class clockActivity extends MainActivity {

    private static final int KEY = 1;
    private TextView tv_time;
    private String sysTimeStr;
    private boolean isVisible = true;
    public LinearLayout layout_1;
    private TextView tv_clock1;
    private boolean flag;
    private static final String ACTION_DATE_CHANGED = Intent.ACTION_DATE_CHANGED;
    private static final String ACTION_TIME_CHANGED = Intent.ACTION_TIME_CHANGED;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        layout_1 = (LinearLayout) findViewById(R.id.linearLayout_id);
        tv_time = findViewById(R.id.ShowTime);
        tv_clock1= findViewById(R.id.tv_clock1);
        new TimeThread().start();
        initView_1();
        layout_1.setVisibility(View.GONE);


    }

    public class TimeThread extends Thread {
        @Override
        public void run () {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = KEY;
                    handler.sendMessage(msg);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while(true);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case KEY :
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    tv_time.setText(simpleDateFormat.format(date));
                    break;
                default:
                    break;
            }
        }
    };



    private void initView_1() {
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_edit1).setOnClickListener(this);
        findViewById(R.id.btn_on).setOnClickListener(this);
        SimpleDateFormat SDF = new SimpleDateFormat("   HH:mm   ");
        Date date = new Date(System.currentTimeMillis());
        tv_clock1.setText(SDF.format(date));
    };

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()) {
            case R.id.btn_add:
                if (isVisible) {
                    layout_1.setVisibility(View.VISIBLE);
                    break;//这一句显示布局LinearLayout区域
                }
                break;

            case R.id.btn_edit1:
                flag=true;
                intent.setClass(getApplicationContext(), EditActivity.class);
                startActivity(intent);
                 break;

            case R.id.btn_on:    //click and turn on the clock service;
                layout_1.setVisibility(View.VISIBLE);
                Bundle bundle = getIntent().getExtras();
                Centime centime = (Centime) bundle.getSerializable("time_mes");
                Log.i("EditActivity","小时为"+centime.getHour());     //get the data
                String v_id = centime.getHour();
                String z_id = centime.getMin();
                tv_clock1.setText("   "+v_id + ":" + z_id+"   ");
                Intent service=new Intent(this, Timeservice.class);  //send data to service
                service.putExtra("time_mes", centime);
                intent.setAction("Timeservice");
                startService(service);

                break;
        }
    }
}


