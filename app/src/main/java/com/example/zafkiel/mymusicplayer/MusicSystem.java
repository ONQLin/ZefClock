package com.example.zafkiel.mymusicplayer;

import com.example.zafkiel.mymusicplayer.MusicInterface;
import com.example.zafkiel.mymusicplayer.MusicActivity;

public class MusicSystem implements MusicInterface {
    private MusicActivity musicActivity;

    @Override
    public void stop_music(){
        musicActivity.stop_music();
    }
}
