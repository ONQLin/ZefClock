package com.example.zafkiel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.zafkiel.Entity.Centime;
import com.example.zafkiel.mymusicplayer.MainActivity_music;

public class EditActivity extends clockActivity {
    Centime centime = new Centime();
    private EditText input_hr, input_min, input_hint;
    public int position;
    public String pw1, pw2, pw3;
    public String path;
    private Button button;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        findViewById(R.id.btn_return).setOnClickListener(this);
        findViewById(R.id.select_music).setOnClickListener(this);

        Intent intent = getIntent();                                                    //通过getIntent()方法实现intent信息的获取
        path = intent.getStringExtra("path");            //获取path
        position = intent.getIntExtra("position",0);

        Switch s=(Switch)findViewById(R.id.switch1);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    centime.setSet_status(true);
                else
                    centime.setSet_status(false);
            }
        });

        button=(Button)findViewById(R.id.jmp_tts);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(EditActivity.this,TTsActivity.class);
                Bundle mBundle=new Bundle();
                mBundle.putSerializable("centime",centime);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_return:
                {
                    input_hr = findViewById(R.id.et_hr);
                    input_min = findViewById(R.id.et_min);
                    input_hint=findViewById(R.id.et_hint);
                    pw1 = input_hr.getText().toString();
                    pw2 = input_min.getText().toString();
                    pw3 = input_hint.getText().toString();
                    Intent intent=new Intent();

                    centime.setHour(pw1);
                    centime.setMin(pw2);
                    centime.setHint(pw3);
                    centime.setPath(path);
                    centime.setPosition(position);
                    centime.setSet_status(true);
                    intent.setClass(getApplicationContext(), clockActivity.class);
                    intent.putExtra("time_mes",centime);
                    //使用putExtra（）传值
                    //intent.putExtra("position", position);
                    startActivity(intent);
                    break;
                }
            case R.id.select_music:
                {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), MainActivity_music.class);
                    startActivity(intent);
                    break;
                }



        }
    }
}
