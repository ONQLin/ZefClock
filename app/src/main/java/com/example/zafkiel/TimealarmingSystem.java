package com.example.zafkiel;

import com.example.zafkiel.Entity.Centime;

public class TimealarmingSystem implements TimeSetInterface {
    private clockActivity clockActivity;

    public void startservice(Centime ct)
    {
        clockActivity.startservice(ct);
    }
}
