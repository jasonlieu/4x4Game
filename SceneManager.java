package com.example.jason.a4x4;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by JASON on 9/4/2018.
 */

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<Scene>();
    public static int ACTIVE_SCENE;
    public static int WIDTH;
    public static int HEIGHT;

    public SceneManager(Context context){
        ACTIVE_SCENE = 0;
        WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
        scenes.add(new MainMenuScene());
        scenes.add(new GameplayScene(context));

    }
    public void receiveTouch(MotionEvent event){
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update(){
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas){
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
