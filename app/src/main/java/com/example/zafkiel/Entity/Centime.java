package com.example.zafkiel.Entity;

import java.io.Serializable;

public class Centime implements Serializable {
    private String hour;
    private String min;
    private String hint;
    private String path;
    private int position;
    private boolean on_status;

    public int getPositon() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    private boolean set_status;

    public boolean isOn_status() {
        return on_status;
    }

    public void setOn_status(boolean on_status) {
        this.on_status = on_status;
    }

    public Centime(){
        hour="XX";
        min="XX";
        hint="";
        path="";
        position=0;
        set_status=false;
        on_status=false;
    }
    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public boolean isSet_status() {
        return set_status;
    }

    public void setSet_status(boolean set_status) {
        this.set_status = set_status;
    }

    public void equal(Centime cty)
    {
        this.hour=cty.getHour();
        this.min=cty.getMin();
        this.hint=cty.getHint();
        this.path=cty.getPath();
        this.position=cty.getPositon();
    }
}
