package com.example.jason.a4x4;

import android.content.Context;
import android.graphics.BlurMaskFilter;
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

public class GameplayScene implements Scene{

    private Rect bgBounds = new Rect(0, 0, WIDTH, HEIGHT);
    private Drawable bg;
    private GameBoard board;
    private static final int SWIPE_THRESHOLD = 100;
    private float DownX = 0, DownY = 0;
    private boolean win;
    private Paint winScreenColor = new Paint();
    private Rect winScreen = new Rect(0,0, WIDTH, HEIGHT);


    GameplayScene(Context context){
        bg = ContextCompat.getDrawable(context, R.drawable.test_bg);
        bg.setBounds(bgBounds);
        board = new GameBoard(context);
        win = false;
        //win = true;
        winScreenColor.setColor(Color.BLACK);
        winScreenColor.setAlpha(100);
        winScreenColor.setMaskFilter(new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL));
    }
    @Override
    public void update(){
        board.update();
    }

    @Override
    public void receiveTouch(MotionEvent event){
        float UpX = 0, UpY = 0, Xdif, Ydif;
        if(!win) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    DownX = event.getX();
                    DownY = event.getY();
                    //System.out.println("CLICK   x: " + DownX + "    y:" +DownY);
                    break;
                case MotionEvent.ACTION_UP:
                    UpX = event.getX();
                    UpY = event.getY();
                    //System.out.println("NOCLICK   x: " + UpX + "    y:" +UpY);
                    Ydif = DownY - UpY;
                    Xdif = UpX - DownX;
                    //System.out.println("DIFFERENCE  x: " + Xdif + "    y:" + Ydif);
                    if (Math.abs(Xdif) > Math.abs(Ydif)) {
                        if (Math.abs(Xdif) > SWIPE_THRESHOLD) {
                            if (DownX > WIDTH * 0.22222 && DownX < WIDTH * 0.77774 && DownY > HEIGHT * 0.5 && DownY < HEIGHT * 0.5 + 4 * WIDTH * 0.13888) {
                                if (Xdif > 0) {
                                    if (DownY > HEIGHT * 0.5 && DownY < HEIGHT * 0.5 + WIDTH * 0.13888) {
                                        board.swipeRight(0);
                                    } else if (DownY > HEIGHT * 0.5 + WIDTH * 0.13888 && DownY < HEIGHT * 0.5 + 2 * WIDTH * 0.13888) {
                                        board.swipeRight(1);
                                    } else if (DownY > HEIGHT * 0.5 + 2 * WIDTH * 0.13888 && DownY < HEIGHT * 0.5 + 3 * WIDTH * 0.13888) {
                                        board.swipeRight(2);
                                    } else if (DownY > HEIGHT * 0.5 + 3 * WIDTH * 0.13888 && DownY < HEIGHT * 0.5 + 4 * WIDTH * 0.13888) {
                                        board.swipeRight(3);
                                    }
                                } else {
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
                                board.startGame();
                                if (board.checkWin()) {
                                    board.stopGame();
                                    win = true;
                                }
                            }
                        }
                    } else if (Math.abs(Ydif) > SWIPE_THRESHOLD) {
                        if (DownY > HEIGHT * 0.5 && DownY < HEIGHT * 0.5 + 4 * WIDTH * 0.13888) {
                            if (Ydif > 0) {
                                if (DownX > WIDTH * 0.2222 && DownX < WIDTH * 0.3611) {
                                    board.swipeUp(0);
                                } else if (DownX > WIDTH * 0.3611 && DownX < WIDTH * 0.49998) {
                                    board.swipeUp(1);
                                } else if (DownX > WIDTH * 0.49998 && DownX < WIDTH * 0.63886) {
                                    board.swipeUp(2);
                                } else if (DownX > WIDTH * 0.63886 && DownX < WIDTH * 0.77774) {
                                    board.swipeUp(3);
                                }
                                board.startGame();
                            } else {
                                if (DownX > WIDTH * 0.2222 && DownX < WIDTH * 0.3611) {
                                    board.swipeDown(0);
                                } else if (DownX > WIDTH * 0.3611 && DownX < WIDTH * 0.49998) {
                                    board.swipeDown(1);
                                } else if (DownX > WIDTH * 0.49998 && DownX < WIDTH * 0.63886) {
                                    board.swipeDown(2);
                                } else if (DownX > WIDTH * 0.63886 && DownX < WIDTH * 0.77774) {
                                    board.swipeDown(3);
                                }
                                board.startGame();
                            }
                            board.startGame();
                            if (board.checkWin()) {
                                board.stopGame();
                                win = true;
                            }
                        }
                    }
            }
        }
    }

    @Override
    public void draw(Canvas canvas){
        //canvas.drawColor(Color.parseColor("#444070")); //#444070
        bg.draw(canvas);
        board.draw(canvas);
        if(win){
            canvas.drawRect( winScreen, winScreenColor);
        }
    }

    @Override
    public void terminate(){
        SceneManager.ACTIVE_SCENE = 0;
    }
}
