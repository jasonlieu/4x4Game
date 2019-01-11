package com.example.jason.a4x4;

import android.content.Context;
import android.database.Cursor;
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
 * Created by JASON on 1/9/2019.
 */

public class ScoreScene implements Scene {
    DatabaseHelper db;
    private Rect bgBounds = new Rect(0, 0, WIDTH, HEIGHT + (int)(HEIGHT * 0.075));
    private Drawable bg;
    long[] scoreArray;
    private Rect backBound = new Rect((int)(WIDTH * 0.05), (int)(HEIGHT * 0.0263), (int)(WIDTH * 0.23), (int)(HEIGHT * 0.05));
    private Drawable backButton;
    private float DownX = 0, DownY = 0;


    private Paint text = new Paint();


    public ScoreScene(Context context){
        db = new DatabaseHelper(context);
        bg = ContextCompat.getDrawable(context, R.drawable.test_bg);
        bg.setBounds(bgBounds);
        backButton = ContextCompat.getDrawable(context, R.drawable.backutton);
        backButton.setBounds(backBound);
        scoreArray = getScores();

        text.setColor(Color.BLACK);
        text.setTextSize(100);
        text.setStyle(Paint.Style.FILL);

    }

    @Override
    public void update(){
        scoreArray = getScores();
    }

    @Override
    public void draw(Canvas canvas){
        bg.draw(canvas);
        backButton.draw(canvas);
        /*canvas.drawText("" + scoreArray[0],(int)(WIDTH * 0.5)-250,(int)(HEIGHT * 0.3), text);
        canvas.drawText("" + scoreArray[1],(int)(WIDTH * 0.5)-250,(int)(HEIGHT * 0.4), text);
        canvas.drawText("" + scoreArray[2],(int)(WIDTH * 0.5)-250,(int)(HEIGHT * 0.5), text);
        canvas.drawText("" + scoreArray[3],(int)(WIDTH * 0.5)-250,(int)(HEIGHT * 0.6), text);
        canvas.drawText("" + scoreArray[4],(int)(WIDTH * 0.5)-250,(int)(HEIGHT * 0.7), text);
        canvas.drawText("" + scoreArray[5],(int)(WIDTH * 0.5)-250,(int)(HEIGHT * 0.8), text);
        canvas.drawText("" + scoreArray[6],(int)(WIDTH * 0.5)-250,(int)(HEIGHT * 0.9), text);*/

        for(int i = 0; i < scoreArray.length; i++){
            if(scoreArray[i] != 0){
                canvas.drawText("" + scoreArray[i],(int)(WIDTH * 0.5)-250,(int)(HEIGHT * ((i * 0.05) + 0.3)), text);
            }
        }
    }

    @Override
    public void receiveTouch(MotionEvent event){
        float UpX, UpY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                DownX = event.getX();
                DownY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                UpX = event.getX();
                UpY = event.getY();
                if((DownX >= 0 && DownX <= (WIDTH * 0.28)) &&       //back button
                        (DownY >= (HEIGHT * 0.02) && DownY <= (HEIGHT * 0.06)) &&
                        (UpX >= 0 && UpX <= (WIDTH * 0.28)) &&
                        (UpY >= (HEIGHT * 0.02) && UpY <= (HEIGHT * 0.06))
                        ){
                    ChangeToMainMenuScene();
                }
        }
    }

    @Override
    public void terminate(){}

    public long[] getScores(){
        long[] scores = new long[10];
        Cursor data = db.getTopScores();
        int index = 0;
        while(data.moveToNext() && index < 10){
            scores[index] = Long.parseLong(data.getString(0));
            index++;
        }

        return scores;
    }
    public void ChangeToMainMenuScene(){
        SceneManager.ACTIVE_SCENE = 0;
    }
}
