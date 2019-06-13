package com.example.zafkiel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zafkiel.Entity.Centime;
import com.example.zafkiel.mymusicplayer.MainActivity_music;
import com.example.zafkiel.mymusicplayer.MusicActivity;
import com.example.zafkiel.DialogActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
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
    public Centime centime;
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
        findViewById(R.id.btn_share).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
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
                intent.setClass(getApplicationContext(), MainActivity_music.class);
                startActivity(intent);
                 break;

            case R.id.btn_share:
                String content=centime.getHour()+centime.getMin()+centime.getHint();
                String folderName = "User";
                File sdCardDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), folderName);

                if (!sdCardDir.exists()) {
                    if (!sdCardDir.mkdirs()) {

                        try {
                            sdCardDir.createNewFile();
                            Log.i("jabot","chuangjian wendang user");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                    //新建文件
                    File saveFile = new File(sdCardDir, "user.txt");

                    if (!saveFile.exists()) {
                        try {
                            saveFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    // FileOutputStream outStream =null;
                    //outStream = new FileOutputStream(saveFile);

                try {
                    final FileOutputStream outStream = new FileOutputStream(saveFile);
                    outStream.write(content.getBytes());
                    outStream.close();
                    Toast.makeText(this, "分享完成", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;

            case R.id.btn_on:    //click and turn on the clock service;
                layout_1.setVisibility(View.VISIBLE);
                Bundle bundle = getIntent().getExtras();
                centime = (Centime) bundle.getSerializable("time_mes");
                Log.i("EditActivity","小时为"+centime.getHour());     //get the data
                String v_id = centime.getHour();
                String z_id = centime.getMin();
                tv_clock1.setText("   "+v_id + ":" + z_id+"   ");
                Intent service=new Intent(this, Timeservice.class);  //send data to service
                service.putExtra("time_mes", centime);
                intent.setAction("Timeservice");
                startService(service);
                break;

            case R.id.btn_del:
                flag=true;
                intent.setClass(getApplicationContext(), EditActivity.class);
                startActivity(intent);
                break;
        }
    }

    private static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }

    public void startservice( Centime ct)
    {
        //get the data
        Intent intent=new Intent();
        Intent service=new Intent(this, Timeservice.class);  //send data to service
        service.putExtra("time_mes", ct);
        intent.setAction("Timeservice");
        startService(service);

    }

}


