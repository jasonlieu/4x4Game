package com.example.jason.a4x4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.util.Random;

import static com.example.jason.a4x4.SceneManager.HEIGHT;
import static com.example.jason.a4x4.SceneManager.WIDTH;

/**
 * Created by JASON on 9/11/2018.
 */

public class GameBoard implements GameObject {

    Square squareList[][] = new Square[4][4];
    Random randomInt = new Random();
    Rect positions[][] = new Rect[4][4];
    Drawable pics[] = new Drawable[16];
    Context context;

    long timeElapsed = 0;
    boolean play = false;
    Paint text = new Paint();
    long time;

    public GameBoard(Context context){
        this.context = context;
        text.setColor(Color.BLACK);
        text.setTextSize(100);
        text.setStyle(Paint.Style.FILL);

        //INITIALIZE GRID
        positions[0][0] = new Rect((int)(WIDTH*0.22222),(int)(HEIGHT * 0.5),(int)(WIDTH*0.22222 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + WIDTH*0.13888));
        positions[0][1] = new Rect((int)(WIDTH*0.3611),(int)(HEIGHT * 0.5),(int)(WIDTH*0.3611 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + WIDTH*0.13888));
        positions[0][2] = new Rect((int)(WIDTH*0.49998),(int)(HEIGHT * 0.5),(int)(WIDTH*0.49998 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + WIDTH*0.13888));
        positions[0][3] = new Rect((int)(WIDTH*0.63886),(int)(HEIGHT * 0.5),(int)(WIDTH*0.63886 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + WIDTH*0.13888));

        positions[1][0] = new Rect((int)(WIDTH*0.22222),(int)(HEIGHT * 0.5 + WIDTH*0.13888),(int)(WIDTH*0.22222 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 2*WIDTH*0.13888));
        positions[1][1] = new Rect((int)(WIDTH*0.3611),(int)(HEIGHT * 0.5 + WIDTH*0.13888),(int)(WIDTH*0.3611 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 2*WIDTH*0.13888));
        positions[1][2] = new Rect((int)(WIDTH*0.49998),(int)(HEIGHT * 0.5 + WIDTH*0.13888),(int)(WIDTH*0.49998 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 2*WIDTH*0.13888));
        positions[1][3] = new Rect((int)(WIDTH*0.63886),(int)(HEIGHT * 0.5 + WIDTH*0.13888),(int)(WIDTH*0.63886 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 2*WIDTH*0.13888));

        positions[2][0] = new Rect((int)(WIDTH*0.22222),(int)(HEIGHT * 0.5 + 2*WIDTH*0.13888),(int)(WIDTH*0.22222 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 3*WIDTH*0.13888));
        positions[2][1] = new Rect((int)(WIDTH*0.3611),(int)(HEIGHT * 0.5 + 2*WIDTH*0.13888),(int)(WIDTH*0.3611 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 3*WIDTH*0.13888));
        positions[2][2] = new Rect((int)(WIDTH*0.49998),(int)(HEIGHT * 0.5 + 2*WIDTH*0.13888),(int)(WIDTH*0.49998 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 3*WIDTH*0.13888));
        positions[2][3] = new Rect((int)(WIDTH*0.63886),(int)(HEIGHT * 0.5 + 2*WIDTH*0.13888),(int)(WIDTH*0.63886 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 3*WIDTH*0.13888));

        positions[3][0] = new Rect((int)(WIDTH*0.22222),(int)(HEIGHT * 0.5 + 3*WIDTH*0.13888),(int)(WIDTH*0.22222 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 4*WIDTH*0.13888));
        positions[3][1] = new Rect((int)(WIDTH*0.3611),(int)(HEIGHT * 0.5 + 3*WIDTH*0.13888),(int)(WIDTH*0.3611 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 4*WIDTH*0.13888));
        positions[3][2] = new Rect((int)(WIDTH*0.49998),(int)(HEIGHT * 0.5 + 3*WIDTH*0.13888),(int)(WIDTH*0.49998 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 4*WIDTH*0.13888));
        positions[3][3] = new Rect((int)(WIDTH*0.63886),(int)(HEIGHT * 0.5 + 3*WIDTH*0.13888),(int)(WIDTH*0.63886 + WIDTH*0.13888),(int)(HEIGHT * 0.5 + 4*WIDTH*0.13888));

        //INITIALIZE SQUARES
        squareList[0][0] = new Square(0, positions[0][0], ContextCompat.getDrawable(context, R.drawable.square_1));
        squareList[0][1] = new Square(1, positions[0][1], ContextCompat.getDrawable(context, R.drawable.square_2));
        squareList[0][2] = new Square(2, positions[0][2], ContextCompat.getDrawable(context, R.drawable.square_3));
        squareList[0][3] = new Square(3, positions[0][3], ContextCompat.getDrawable(context, R.drawable.square_4));

        squareList[1][0] = new Square(4, positions[1][0], ContextCompat.getDrawable(context, R.drawable.square_5));
        squareList[1][1] = new Square(5, positions[1][1], ContextCompat.getDrawable(context, R.drawable.square_6));
        squareList[1][2] = new Square(6, positions[1][2], ContextCompat.getDrawable(context, R.drawable.square_7));
        squareList[1][3] = new Square(7, positions[1][3], ContextCompat.getDrawable(context, R.drawable.square_8));

        squareList[2][0] = new Square(8, positions[2][0], ContextCompat.getDrawable(context, R.drawable.square_9));
        squareList[2][1] = new Square(9, positions[2][1], ContextCompat.getDrawable(context, R.drawable.square_10));
        squareList[2][2] = new Square(10, positions[2][2], ContextCompat.getDrawable(context, R.drawable.square_11));
        squareList[2][3] = new Square(11, positions[2][3], ContextCompat.getDrawable(context, R.drawable.square_12));

        squareList[3][0] = new Square(12, positions[3][0], ContextCompat.getDrawable(context, R.drawable.square_13));
        squareList[3][1] = new Square(13, positions[3][1], ContextCompat.getDrawable(context, R.drawable.square_14));
        squareList[3][2] = new Square(14, positions[3][2], ContextCompat.getDrawable(context, R.drawable.square_15));
        squareList[3][3] = new Square(15, positions[3][3], ContextCompat.getDrawable(context, R.drawable.square_16));

        //INITIATE PICTURE ARRAY
        pics[0] = ContextCompat.getDrawable(context, R.drawable.square_1);
        pics[1] = ContextCompat.getDrawable(context, R.drawable.square_2);
        pics[2] = ContextCompat.getDrawable(context, R.drawable.square_3);
        pics[3] = ContextCompat.getDrawable(context, R.drawable.square_4);
        pics[4] = ContextCompat.getDrawable(context, R.drawable.square_5);
        pics[5] = ContextCompat.getDrawable(context, R.drawable.square_6);
        pics[6] = ContextCompat.getDrawable(context, R.drawable.square_7);
        pics[7] = ContextCompat.getDrawable(context, R.drawable.square_8);
        pics[8] = ContextCompat.getDrawable(context, R.drawable.square_9);
        pics[9] = ContextCompat.getDrawable(context, R.drawable.square_10);
        pics[10] = ContextCompat.getDrawable(context, R.drawable.square_11);
        pics[11] = ContextCompat.getDrawable(context, R.drawable.square_12);
        pics[12] = ContextCompat.getDrawable(context, R.drawable.square_13);
        pics[13] = ContextCompat.getDrawable(context, R.drawable.square_14);
        pics[14] = ContextCompat.getDrawable(context, R.drawable.square_15);
        pics[15] = ContextCompat.getDrawable(context, R.drawable.square_16);

        randomizeBoard();
    }
    public void swipeUp(int column){
        int tempNum = squareList[0][column].getNum();
        squareList[0][column].change(squareList[1][column].getNum(), pics[squareList[1][column].getNum()]);
        squareList[1][column].change(squareList[2][column].getNum(), pics[squareList[2][column].getNum()]);
        squareList[2][column].change(squareList[3][column].getNum(), pics[squareList[3][column].getNum()]);
        squareList[3][column].change(tempNum, pics[tempNum]);
    }
    public void swipeDown(int column){
        int tempNum = squareList[3][column].getNum();
        squareList[3][column].change(squareList[2][column].getNum(), pics[squareList[2][column].getNum()]);
        squareList[2][column].change(squareList[1][column].getNum(), pics[squareList[1][column].getNum()]);
        squareList[1][column].change(squareList[0][column].getNum(), pics[squareList[0][column].getNum()]);
        squareList[0][column].change(tempNum, pics[tempNum]);
    }
    public void swipeRight(int row){
        int tempNum = squareList[row][3].getNum();
        squareList[row][3].change(squareList[row][2].getNum(), pics[squareList[row][2].getNum()]);
        squareList[row][2].change(squareList[row][1].getNum(), pics[squareList[row][1].getNum()]);
        squareList[row][1].change(squareList[row][0].getNum(), pics[squareList[row][0].getNum()]);
        squareList[row][0].change(tempNum, pics[tempNum]);
    }
    public void swipeLeft(int row){
        int tempNum = squareList[row][0].getNum();
        squareList[row][0].change(squareList[row][1].getNum(), pics[squareList[row][1].getNum()]);
        squareList[row][1].change(squareList[row][2].getNum(), pics[squareList[row][2].getNum()]);
        squareList[row][2].change(squareList[row][3].getNum(), pics[squareList[row][3].getNum()]);
        squareList[row][3].change(tempNum, pics[tempNum]);
    }
    public void startGame(){
        play = true;
        time = System.currentTimeMillis()/1000;
    }
    public void stopGame(){
        play = false;
    }
    public boolean checkWin(){
        for(int i = 0 ; i <= 15; i++){
            if(squareList[i/4][i%4].getNum() != i){return false;}
        }
        return true;
    }
    public void randomizeBoard(){
        int ranMoves = randomInt.nextInt(50) + 25;
        for(int i = 0; i <  ranMoves; i++){
            int ranSwipe = randomInt.nextInt(4);
            final int ranLine = randomInt.nextInt(4);
            switch(ranSwipe){
                case 0:
                    swipeUp(ranLine);
                    break;
                case 1:
                    swipeDown(ranLine);
                    break;
                case 2:
                    swipeRight(ranLine);
                    break;
                case 3:
                    swipeLeft(ranLine);
                    break;
            }
        }
    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawText(""+ timeElapsed,((WIDTH/2)),(int)(HEIGHT * 0.25), text);

        squareList[0][0].draw(canvas);
        squareList[0][1].draw(canvas);
        squareList[0][2].draw(canvas);
        squareList[0][3].draw(canvas);

        squareList[1][0].draw(canvas);
        squareList[1][1].draw(canvas);
        squareList[1][2].draw(canvas);
        squareList[1][3].draw(canvas);

        squareList[2][0].draw(canvas);
        squareList[2][1].draw(canvas);
        squareList[2][2].draw(canvas);
        squareList[2][3].draw(canvas);

        squareList[3][0].draw(canvas);
        squareList[3][1].draw(canvas);
        squareList[3][2].draw(canvas);
        squareList[3][3].draw(canvas);
    }
    public long getScore(){
        return timeElapsed;
    }
    @Override
    public void update(){
        if(play) {
            if(time != (int)(System.currentTimeMillis()/1000)){
                time = (int)(System.currentTimeMillis()/1000);
                timeElapsed++;
            }
        }
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                squareList[r][c].update();
            }
        }
    }
}
