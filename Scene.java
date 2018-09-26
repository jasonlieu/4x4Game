package com.example.jason.a4x4;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by JASON on 9/4/2018.
 */

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
