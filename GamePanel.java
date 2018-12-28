package com.example.jason.a4x4;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by JASON on 8/30/2018.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
        private MainThread thread;
        private SceneManager manager;
        public GamePanel(Context context){
            super(context);
            getHolder().addCallback(this);
            thread = new MainThread(getHolder(), this);
            manager = new SceneManager(context);
            setFocusable(true);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder){
            thread = new MainThread(getHolder(), this);
            if (thread != null) {
                thread.setRunning(true);
                thread.start();
            }

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder){
            if (thread != null) {
                thread.setRunning(false);
            }

        }
        @Override
        public boolean onTouchEvent(MotionEvent event){
            manager.receiveTouch(event);
            return true;
        }

        public void update(){
            manager.update();
        }

        @Override
        public void draw(Canvas canvas){
            super.draw(canvas);
            manager.draw(canvas);
        }
}
