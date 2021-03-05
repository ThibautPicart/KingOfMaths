package com.example.projetandroid_picart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Random;

/**
 * Created by Thibaut Picart on 11/12/20.
 */
public class GyroView extends SurfaceView implements SurfaceHolder.Callback{

    Paint myTitlePaint;
    Paint myViseurPaint;
    Paint myTextPaint;
    SurfaceHolder holder;
    protected AnimThread thread = null;

    private Context mContext;

    //position du viseur
    private int xV;
    private int yV;

    //déplacement
    private int dX=0;
    private int dY=0;

    //rayon
    private int rV = 30;

    //equation

    private int rdm1;
    private int rdm2;
    private int soluce;
    private int fakeSol1;
    private int fakeSol2;
    private int pos;
    private String soluce1;
    private String soluce2;
    private String soluce3;
    private float xSol;
    private float ySol;

    // popup
    private String text = "Correct !";

    boolean resolved = false;


    public GyroView(SoloGyroActivity context){
        super(context);
        mContext = context;
        setWillNotDraw(false);

        myViseurPaint=new Paint();
        myViseurPaint.setColor(Color.BLUE);
        myViseurPaint.setStrokeWidth(3);

        //holder
        holder = getHolder();
        holder.addCallback(this);


        // init of equation
        initEq();

    }



    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        // configuration
        myTitlePaint = new Paint();
        myTextPaint = new Paint();

        myTextPaint.setColor(Color.BLACK);
        myTextPaint.setTextSize(20);

        myTitlePaint.setARGB(200, 255, 152, 0);
        myTitlePaint.setTextAlign(Paint.Align.CENTER);
        myTitlePaint.setTextSize(30);


        drawText(canvas);

    }

    public void drawText(Canvas canvas){
        canvas.save();

        // enoncé
        canvas.drawText("Mode : Tourner le téléphone", getWidth()/2, getHeight()/10, myTitlePaint);
        canvas.drawText(Integer.toString(rdm1), getWidth()/4,3*getHeight()/10, myTextPaint );
        canvas.drawText(Integer.toString(rdm2), 3*getWidth()/4, 3*getHeight()/10, myTextPaint);
        canvas.drawText("+", getWidth()/2, 3*getHeight()/10, myTextPaint);

        myTitlePaint.setColor(Color.BLACK);
        canvas.drawText("Quelle est la solution ?", getWidth()/2, 2*getHeight()/5, myTitlePaint);

        //solutions
        canvas.drawText(soluce1, getWidth()/4, 7*getHeight()/10, myTextPaint);
        canvas.drawText(soluce3, 3*getWidth()/4, 7*getHeight()/10, myTextPaint);
        myTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(soluce2, getWidth()/2, 9*getHeight()/10, myTextPaint);

        switch(pos){
            case 1:
                xSol =getWidth()/4;
                ySol = 7*getHeight()/10;
                break;
            case 2:
                xSol = 2*getWidth()/5;
                ySol = 9*getHeight()/10;
                break;
            case 3:
                xSol = 3*getWidth()/4;
                ySol = 7*getHeight()/10;
                break;
        }
    }



    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        // on créé et lance un thread d'animation

        //position à l'initialisation
        xV = getWidth()/2;
        yV=getHeight()/2;

        // on lance le thread
        thread = new AnimThread(this);
        thread.setRunning(true);
        thread.start();


    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        // on arrête le thread d'animation
        thread.setRunning(false);
    }

    public void drawViseur(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(xV, yV, rV, myViseurPaint);

    }

    public void updatePos(int Gx, int Gy){
        if(xV+Gy<getWidth()){
            if(xV+Gy>0){
                xV+=Gy;
            }
            else xV=0;
        }
        else xV=getWidth();

        if(yV+Gx<getHeight()){
            if(yV+Gx>0){
                yV+=Gx;
            }
            else yV=0;
        }
        else yV=getHeight();

        //Log.v( "positions" , " xV = " + xV + " " + " yV = " + yV + " " + " xSol = " + xSol + " " + "ySol = " + ySol);
        // pos viseur = pos solution ?
        if(((xV-xSol<30 && xV-xSol>0)||(xSol-xV<30 && xSol-xV>0))&&((yV-ySol<30 && yV-ySol>0)||(ySol-yV<30 && ySol-yV>0))){

            //Log.i("Result ->", text);
            if(xSol!=0.0 && ySol!=0.0){
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                resolved=true;
            }

        }


    } // update pos

    private void initEq() {
        rdm1 = new Random().nextInt(10) + 1;
        rdm2 = new Random().nextInt(10) + 1;
        soluce=rdm1+rdm2;

        pos = new Random().nextInt(3) + 1;
        fakeSol1 = new Random().nextInt(10) + 1;
        fakeSol2 = new Random().nextInt(10) + 1;

        while(fakeSol1==soluce){
            fakeSol1 = new Random().nextInt(20) + 1;
        }
        while(fakeSol2==soluce || fakeSol2==fakeSol1){
            fakeSol2 = new Random().nextInt(20) + 1;
        }


        if(pos==1){
            soluce1=Integer.toString(soluce);
            soluce2=Integer.toString(fakeSol1);
            soluce3=Integer.toString(fakeSol2);

        }
        else if (pos==2){
            soluce1=Integer.toString(fakeSol1);
            soluce2=Integer.toString(soluce);
            soluce3=Integer.toString(fakeSol2);

        }
        else if (pos==3){
            soluce1=Integer.toString(fakeSol2);
            soluce2=Integer.toString(fakeSol1);
            soluce3=Integer.toString(soluce);

        }

    }//init


}



