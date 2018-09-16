package com.example.jason.a4x4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import static com.example.jason.a4x4.SceneManager.HEIGHT;
import static com.example.jason.a4x4.SceneManager.WIDTH;

/**
 * Created by JASON on 9/7/2018.
 */

public class Square implements GameObject {

    private Drawable picture;
    private Rect rectangle;
    private int number;

    Square(int number, Rect rectangle, Drawable picture){
        this.number = number;
        this.rectangle = rectangle;
        this.picture = picture;
        this.picture.setBounds(rectangle);
    }

    @Override
    public void draw(Canvas canvas){
        picture.draw(canvas);
    }

    @Override
    public void update(){
        picture.invalidateSelf();
    }
    public void update(int number, Drawable picture){};
    public Drawable getPic(){
        return picture;
    }
    public int getNum(){
        return number;
    }
    public void change(int number, Drawable picture){

        this.number = number;
        this.picture = picture;
        picture.setBounds(rectangle);

    }
    public void move(Rect rectangle){
        this.rectangle = rectangle;
        picture.setBounds(rectangle);
    }

}
