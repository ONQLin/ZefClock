package com.example.zafkiel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.zafkiel.Entity.Centime;

public class EditActivity extends clockActivity {

    private EditText input_hr, input_min, input_hint;
    public String pw1, pw2, pw3;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        findViewById(R.id.btn_return).setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_return:
                {
                    input_hr = findViewById(R.id.et_hr);
                    input_min = findViewById(R.id.et_min);
                    pw1 = input_hr.getText().toString();
                    pw2 = input_min.getText().toString();
                    pw3 = input_hint.getText().toString();
                    Intent intent = new Intent();
                    Centime centime = new Centime();
                    centime.setHour(pw1);
                    centime.setMin(pw2);
                    centime.setHint(pw3);
                    centime.setSet_status(true);
                    intent.putExtra("time_mes", centime);
                    intent.setClass(getApplicationContext(), clockActivity.class);
                    startActivity(intent);
                    break;
                }



        }
    }
}
