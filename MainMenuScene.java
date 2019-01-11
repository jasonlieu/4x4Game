package com.example.jason.a4x4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;

import static com.example.jason.a4x4.SceneManager.ACTIVE_SCENE;
import static com.example.jason.a4x4.SceneManager.HEIGHT;
import static com.example.jason.a4x4.SceneManager.WIDTH;

/**
 * Created by JASON on 9/4/2018.
 */

public class MainMenuScene implements Scene {
    private Paint text = new Paint();
    private Rect bgBounds = new Rect(0, 0, WIDTH, HEIGHT + (int)(HEIGHT * 0.075));
    private Drawable bg;
    private Rect playBounds = new Rect((int)(WIDTH * 0.5 - (WIDTH*2*0.13888)), (int)(HEIGHT * 0.7 + (HEIGHT * 0.075)),
                                        (int)(WIDTH * 0.5 + (WIDTH*0.13888)), (int)(HEIGHT *0.7 + WIDTH*0.13888 + (HEIGHT * 0.075)));
    private Drawable playButton;
    private Rect scoreBounds = new Rect((int)(WIDTH * 0.5 + (WIDTH*0.13888) - (WIDTH*0.001)), (int)(HEIGHT * 0.7 + (HEIGHT * 0.075)),
                                        (int)(WIDTH * 0.5 + (WIDTH*2*0.13888)- (WIDTH*0.001)),(int)(HEIGHT * 0.7 + (WIDTH * 0.13888) + (HEIGHT * 0.075)));
    private Drawable scoreButton;
    //private Rect infoBounds = new Rect((int)(WIDTH * 0.5 + (WIDTH*0.13888)/2 - (WIDTH*0.005)), (int)(HEIGHT * 0.7 - (WIDTH*0.005)),
    //                                    (int)(WIDTH * 0.5 + (WIDTH*0.13888)/2 + (WIDTH*0.13888)),(int)(HEIGHT * 0.7 + (WIDTH*0.13888)));
    //private Drawable infoButton;

    MainMenuScene(Context context){
        bg = ContextCompat.getDrawable(context, R.drawable.test_bg);
        bg.setBounds(bgBounds);
        playButton = ContextCompat.getDrawable(context, R.drawable.playbutton);
        playButton.setBounds((playBounds));
        scoreButton = ContextCompat.getDrawable(context, R.drawable.scorebuttont);
        scoreButton.setBounds(scoreBounds);
        //infoButton = ContextCompat.getDrawable(context, R.drawable.menublank);
        //infoButton.setBounds(infoBounds);

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
                if(playBounds.contains((int)event.getX(), (int)event.getY()))
                {
                    ChangeToPlayScene();
                }
                else if(scoreBounds.contains((int)event.getX(), (int)event.getY())){
                    ChangeToScoreScene();
                }
        }
    }

    @Override
    public void draw(Canvas canvas){
        bg.draw(canvas);
        playButton.draw(canvas);
        scoreButton.draw(canvas);
        //infoButton.draw(canvas);
        canvas.drawText("mainmenu",(int)(WIDTH * 0.5)-250,(int)(HEIGHT * 0.3), text);
    }

    @Override
    public void terminate(){}

    public void ChangeToPlayScene(){
        SceneManager.ACTIVE_SCENE = 1;
    }
    public void ChangeToScoreScene(){SceneManager.ACTIVE_SCENE = 2;}
}
