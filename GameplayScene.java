package com.example.jason.a4x4;

import android.content.Context;
import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;

import static com.example.jason.a4x4.SceneManager.HEIGHT;
import static com.example.jason.a4x4.SceneManager.WIDTH;

/**
 * Created by JASON on 9/4/2018.
 */

public class GameplayScene implements Scene{
    GameBoard board;
    private static final int SWIPE_THRESHOLD = 100;
    float DownX = 0, DownY = 0;

    GameplayScene(Context context){
        board = new GameBoard(context);
    }
    @Override
    public void update(){
        board.update();
    }

    @Override
    public void receiveTouch(MotionEvent event){
        float UpX = 0, UpY = 0, Xdif, Ydif;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                DownX = event.getX();
                DownY = event.getY();
                System.out.println("CLICK   x: " + DownX + "    y:" +DownY);
                break;
            case MotionEvent.ACTION_UP:
                UpX = event.getX();
                UpY = event.getY();
                System.out.println("NOCLICK   x: " + UpX + "    y:" +UpY);
                Ydif = DownY - UpY;
                Xdif = UpX - DownX;
                System.out.println("DIFFERENCE  x: " + Xdif + "    y:" + Ydif);
                if (Math.abs(Xdif) > Math.abs(Ydif)) {
                    if (Math.abs(Xdif) > SWIPE_THRESHOLD) {
                        if (Xdif > 0) {
                            if(DownX > WIDTH * 0.22222 && DownX < WIDTH * 0.77774) {
                                if (DownY > HEIGHT * 0.5 && DownY < HEIGHT * 0.5 + WIDTH * 0.13888) {
                                    board.swipeRight(0);
                                } else if (DownY > HEIGHT * 0.5 + WIDTH * 0.13888 && DownY < HEIGHT * 0.5 + 2 * WIDTH * 0.13888) {
                                    board.swipeRight(1);
                                } else if (DownY > HEIGHT * 0.5 + 2 * WIDTH * 0.13888 && DownY < HEIGHT * 0.5 + 3 * WIDTH * 0.13888) {
                                    board.swipeRight(2);
                                } else if (DownY > HEIGHT * 0.5 + 3 * WIDTH * 0.13888 && DownY < HEIGHT * 0.5 + 4 * WIDTH * 0.13888) {
                                    board.swipeRight(3);
                                }
                            }
                        } else {
                            if(DownX > WIDTH * 0.22222 && DownX < WIDTH * 0.77774) {
                                if (DownY > HEIGHT * 0.5 && DownY < HEIGHT * 0.5 + WIDTH * 0.13888) {
                                    board.swipeLeft(0);
                                } else if (DownY > HEIGHT * 0.5 + WIDTH * 0.13888 && DownY < HEIGHT * 0.5 + 2 * WIDTH * 0.13888) {
                                    board.swipeLeft(1);
                                } else if (DownY > HEIGHT * 0.5 + 2 * WIDTH * 0.13888 && DownY < HEIGHT * 0.5 + 3 * WIDTH * 0.13888) {
                                    board.swipeLeft(2);
                                } else if (DownY > HEIGHT * 0.5 + 3 * WIDTH * 0.13888 && DownY < HEIGHT * 0.5 + 4 * WIDTH * 0.13888) {
                                    board.swipeLeft(3);
                                }
                            }
                        }
                    }
                }
                else if (Math.abs(Ydif) > SWIPE_THRESHOLD) {
                    if (Ydif > 0) {
                        System.out.println("UP");
                    } else {
                        System.out.println("DOWN");
                    }
                }

        }

    }

    @Override
    public void draw(Canvas canvas){
        board.draw(canvas);
    }

    @Override
    public void terminate(){
        SceneManager.ACTIVE_SCENE = 0;
    }
}
