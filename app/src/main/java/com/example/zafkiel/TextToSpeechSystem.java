package com.example.zafkiel;

import android.speech.tts.TextToSpeech;
import android.widget.Toast;
import android.speech.tts.TextToSpeech.OnInitListener;
import org.w3c.dom.Text;
import com.example.zafkiel.TTsActivity;
import com.example.zafkiel.Entity.Centime;
import com.example.zafkiel.TextToVoiceInterface;
import java.util.Locale;

public class TextToSpeechSystem  implements  TextToVoiceInterface{
    TextToSpeech ttspeech;
    @Override
    public void play(String notes){

        ttspeech.speak(notes,TextToSpeech.QUEUE_FLUSH,null);
    }
    @Override
    public void get_tts(TextToSpeech tes){
        ttspeech=tes;
    }
    public  void main(){
    }


}