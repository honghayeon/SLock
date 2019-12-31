package com.example.slock;

public class Log {
    String time;
    String state;

    public Log() {}
    public Log(String time, String state) {
        this.time = time;
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public String getState() {
        return state;
    }
}