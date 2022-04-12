package com.example.week09_lecture;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private String KEY_NAME = "com.example.week09_lecture.counterapp.KEY_NAME";
    private String KEY_COUNTER = "com.example.week09_lecture.counterapp.KEY_COUNTER";

    Context context;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.context = context;
        settings = context.getSharedPreferences("com.example.week09_lecture.counterapp",MODE_PRIVATE);
        editor = settings.edit();
    }

    public void setName(String name){
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public String getName(){
        String value = settings.getString(KEY_NAME, "");
        return value;
    }

    public void setCounter(int value){
        editor.putInt(KEY_COUNTER, value);
        editor.commit();
    }

    public int getCounter(){
        int value = settings.getInt(KEY_COUNTER,0);
        return value;
    }
}
