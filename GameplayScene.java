package com.example.jason.a4x4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

/**
 * Created by JASON on 9/4/2018.
 */

public class GameplayScene implements Scene {
    @Override
    public void update(){

    }

    @Override
    public void receiveTouch(MotionEvent event){

    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
    }

    @Override
    public void terminate(){
        SceneManager.ACTIVE_SCENE = 0;
    }
}
