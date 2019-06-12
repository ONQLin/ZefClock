package com.example.zafkiel;

import android.speech.tts.TextToSpeech;
import com.example.zafkiel.Entity.Centime;
public interface TextToVoiceInterface {
    public void play(Centime clk);
    public void get_tts(TextToSpeech tts);
}
