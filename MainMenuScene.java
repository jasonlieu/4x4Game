package com.example.jason.a4x4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;

import static com.example.jason.a4x4.SceneManager.HEIGHT;
import static com.example.jason.a4x4.SceneManager.WIDTH;

/**
 * Created by JASON on 9/4/2018.
 */

public class MainMenuScene implements Scene {
    private Paint text = new Paint();
    private Rect bgBounds = new Rect(0, 0, WIDTH, HEIGHT);
    private Drawable bg;

    MainMenuScene(Context context){
        bg = ContextCompat.getDrawable(context, R.drawable.test_bg);
        bg.setBounds(bgBounds);
        text.setColor(Color.BLACK);
        text.setTextSize(100);
        text.setStyle(Paint.Style.FILL);
    }


    @Override
    public void update(){

    }

    @Override
    public void receiveTouch(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if((int) event.getX() >= (WIDTH/2 - 100) &&                     //button location
                        (int) event.getX() <= (WIDTH/2 + 100) &&                //press button
                        (int) event.getY() >= (HEIGHT * 0.7 - 100) &&           //touch = true, used for cases in update()
                        (int) event.getY() <= (HEIGHT * 0.7 + 100))
                {
                    terminate();
                }
        }
    }

    @Override
    public void draw(Canvas canvas){
        //canvas.drawColor(Color.LTGRAY);
        bg.draw(canvas);
        canvas.drawText("mainmenu",(int)(WIDTH * 0.5)-250,(int)(HEIGHT * 0.3), text);
        canvas.drawText("playButton",(WIDTH/2 - 250), (int)(HEIGHT * 0.7),text);
    }

    @Override
    public void terminate(){
        SceneManager.ACTIVE_SCENE = 1;
    }
}
