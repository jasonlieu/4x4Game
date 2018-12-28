package com.example.jason.a4x4;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    GamePanel gamePanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate");
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        gamePanel = new GamePanel(MainActivity.this);
        setContentView(gamePanel);
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("onPause");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        System.out.println("onRestart");
    }

    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("onStart");
    }

    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("onStop");
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("onResume");
    }
}
