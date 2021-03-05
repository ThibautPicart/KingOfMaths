package com.example.projetandroid_picart;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Thibaut Picart on 11/12/20.
 */
public class AnimThread extends Thread{
    private long time;
    private final int fps = 20;
    private boolean toRun = false;
    private GyroView mView;
    private SurfaceHolder surfaceHolder;

    public AnimThread(GyroView view){
        mView = view;
        surfaceHolder = mView.getHolder();
    }

    public void setRunning(boolean run) {
        toRun = run;
    }

    @Override
    public void run(){
        Canvas c;
        if(!toRun) {return;}
        while(toRun){
            long cTime = System.currentTimeMillis();
            if((cTime-time) <= (1000/fps)) {
                c = null;
                try {
                    c = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder)
                    {
                        if(c!=null) {mView.drawViseur(c);};

                    }
                } finally {
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
            time = cTime;
        }
    }
}
