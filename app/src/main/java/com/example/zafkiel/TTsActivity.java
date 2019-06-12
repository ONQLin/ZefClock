package com.example.zafkiel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.content.Intent;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;
import java.util.Locale;
import android.widget.Toast;
import com.example.zafkiel.Entity.Centime;
import com.example.zafkiel.TextToVoiceInterface;
import org.w3c.dom.Text;

public class TTsActivity extends AppCompatActivity {
    Centime clk;
    String notes;
    private TextToSpeech ttspeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);

        clk=(Centime) getIntent().getSerializableExtra("centime");

        Switch s=(Switch)findViewById(R.id.switch1);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    clk.setSet_status(true);
                else
                    clk.setSet_status(false);
            }
        });

        Button testbutton=(Button)findViewById(R.id.button) ;
        testbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputtext=(EditText)findViewById(R.id.testtext);
                notes=inputtext.getText().toString();
                TextView out=(TextView)findViewById(R.id.outputtext);
                out.setText(notes);
                ttspeech.speak(notes,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        Button bt2=(Button)findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputtext=(EditText)findViewById(R.id.testtext);
                notes=inputtext.getText().toString();
                TextView out=(TextView)findViewById(R.id.outputtext);
                out.setText(notes);
                TextToVoiceInterface ttsp =new TextToSpeechSystem();
                ttsp.get_tts(ttspeech);
                notes=clk.getHint();
                ttsp.play(clk);
            }
        });

        ttspeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int supported=ttspeech.setLanguage(Locale.CHINA);
                    if((supported!=TextToSpeech.LANG_AVAILABLE)&&(supported!=TextToSpeech.LANG_COUNTRY_AVAILABLE)){
                        Toast.makeText(TTsActivity.this,"不支持当前语言",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


}
