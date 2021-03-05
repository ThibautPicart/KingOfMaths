package com.example.projetandroid_picart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static java.lang.String.valueOf;

/**
 * Created by Thibaut Picart on 10/12/20.
 */
public class SoloGyroActivity extends AppCompatActivity  /*implements SensorEventListener*/{

    GyroView myView;

    // vibrator

    private Vibrator vibrator;

    // Gyro

    private SensorManager mSensorManager;
    private Sensor mGyro;
    private SensorEventListener mGyroListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gyro

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mGyro = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        mGyroListener = new SensorEventListener() {

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Do something here if sensor accuracy changes
            }

            @Override
            public void onSensorChanged(SensorEvent event){
                float Gx = event.values[0];
                float Gy = event.values[1];
                //float Gz = event.values[2];
                /*Log . v ( "sensor" , " TimeAcc = " + event . timestamp + " Gx = " + Gx + " " + " Ay = " + Gy + " " +
                        " Az = " + Gz );*/
                myView.updatePos((int)(100*Gx), (int)(100*Gy));

                if(myView.resolved)
                {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }

        }; //listener



        myView = new GyroView(this);
        setContentView(myView);


        // Vibrator
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);



    } // on create



    //vibrate
    private void vib() {
        vibrator.vibrate(1000);
    }

    //on resume
    @Override
    protected void onResume() {
        super.onResume();
        if(mSensorManager!=null) mSensorManager.registerListener(mGyroListener, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause
    @Override
    protected void onPause() {
        super.onPause();
        if(mSensorManager!=null) mSensorManager.unregisterListener(mGyroListener);
    }



} // class gyroActivity
