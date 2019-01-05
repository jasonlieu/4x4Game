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

    private Context context;
    private Rect bgBounds = new Rect(0, 0, WIDTH, HEIGHT);
    private Drawable bg;
    private GameBoard board;
    private static final int SWIPE_THRESHOLD = 100;
    private float DownX = 0, DownY = 0;
    private boolean win;
    private Paint winScreenColor = new Paint();
    private Rect winScreen = new Rect(0,0, WIDTH, HEIGHT);
    private Rect resetBound = new Rect((int)(WIDTH * 0.77), (int)(HEIGHT * 0.0263), (int)(WIDTH * 0.95), (int)(HEIGHT * 0.05));

    private Drawable resetButton;

    GameplayScene(Context context){
        this.context = context;
        bg = ContextCompat.getDrawable(context, R.drawable.test_bg);
        bg.setBounds(bgBounds);
        resetButton = ContextCompat.getDrawable(context, R.drawable.resetbutton);
        resetButton.setBounds(resetBound);
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
                    break;
                case MotionEvent.ACTION_UP:
                    UpX = event.getX();
                    UpY = event.getY();
                    Ydif = DownY - UpY;
                    Xdif = UpX - DownX;
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
                    } else if( //reset button
                            (DownX >= (WIDTH * 0.73) && DownX <= (WIDTH * 0.99)) && //check in resetBounds
                            (DownY >= (HEIGHT * 0.02) && DownY <= (HEIGHT * 0.06)) &&
                            (UpX >= (WIDTH * 0.73) && UpX <= (WIDTH * 0.99)) &&
                            (UpY >= (HEIGHT * 0.02) && UpY <= (HEIGHT * 0.06))
                            ){
                                board = new GameBoard(context);
                    }
            }
        }
    }

    @Override
    public void draw(Canvas canvas){
        bg.draw(canvas);
        board.draw(canvas);
        resetButton.draw(canvas);
        if(win){
            canvas.drawRect( winScreen, winScreenColor);
        }
    }

    @Override
    public void terminate(){
        SceneManager.ACTIVE_SCENE = 0;
    }
}
