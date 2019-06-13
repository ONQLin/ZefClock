package com.example.zafkiel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity {

    String notes;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();                                                    //通过getIntent()方法实现intent信息的获取
        notes = intent.getStringExtra("notes");


        new AlertDialog.Builder(DialogActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("闹钟提醒")
                .setMessage(notes)
                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();//Exit Activity
                    }
                })
                .create().show();

    }
}
