package com.example.zafkiel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText input_usr;
    private EditText input_pw;
    private String pw = "";

    public static String stringToAscii(String value) //string转数字（ascii相加）
    {
        int sub = 0;
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int temp = chars[i];
            sub += temp;
        }
        String str = Integer.toString(sub);
        return str;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        input_usr = findViewById(R.id.et_username);
        input_pw = findViewById(R.id.et_pw);
        pw = input_pw.getText().toString();
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:    //判定密码为注册码则成功登陆跳转
                if (input_pw.getText().toString().equals(stringToAscii(input_usr.getText().toString()))) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), clockActivity.class);
                    startActivity(intent);
                    break;
                } else {
                    Toast.makeText(this, "无法登陆，点击注册获得密码", Toast.LENGTH_LONG).show();
                    break;
                }
                //登陆
            case R.id.btn_register:
                Toast.makeText(this, stringToAscii(input_usr.getText().toString()), Toast.LENGTH_LONG).show();
                break;
            //显示密码

        }
    }
}
