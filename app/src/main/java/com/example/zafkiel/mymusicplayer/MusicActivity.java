package com.example.zafkiel.mymusicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.zafkiel.EditActivity;
import com.example.zafkiel.Entity.DataChangeReceiver;
import com.example.zafkiel.R;
import com.example.zafkiel.mymusicplayer.Common;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "MusicActivity";
    private int position;
    private String musicPath;
    private ImageView stopImgv;
    private boolean isStop;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);


        bingID();                                                                           //调用bingID();方法实现对控件的绑定

        Intent intent = getIntent();                                                    //通过getIntent()方法实现intent信息的获取
        musicPath = intent.getStringExtra("musicPath");

        mediaPlayer = new MediaPlayer();
        play(musicPath);

    }

    private void bingID(){
        stopImgv = findViewById(R.id.music_stop_imgv);
    }

    private void play(String path){
        isStop = false;
        mediaPlayer.reset();
        try{
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Log.i("music", "123");
            mediaPlayer.setOnCompletionListener((new MediaPlayer.OnCompletionListener() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if(!mediaPlayer.isPlaying()){
                                mediaPlayer.reset();
                                play(musicPath);
                            }
                        }
                    })

            );
        }catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e){
            e.printStackTrace();
        }
        MusicThread musicThread = new MusicThread();                                         //启动线程
        new Thread(musicThread).start();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_stop_imgv:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                break;
            default:
                break;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    //创建一个类MusicThread实现Runnable接口，实现多线程
    class MusicThread implements Runnable {

        @Override
        public void run() {
            while (!isStop && Common.musicList.get(position) != null) {
                try {
                    //让线程睡眠1000毫秒
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //放送给Handler现在的运行到的时间，进行ui更新
                handler.sendEmptyMessage(mediaPlayer.getCurrentPosition());

            }
        }
    }
}
