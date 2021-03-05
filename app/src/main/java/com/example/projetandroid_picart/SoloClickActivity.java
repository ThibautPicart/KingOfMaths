package com.example.projetandroid_picart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class SoloClickActivity extends AppCompatActivity {
    private TextView int1Text;
    private TextView int2Text;

    private int rdm1;
    private int rdm2;

    private Button soluce1;
    private Button soluce2;
    private Button soluce3;

    private int soluce;
    private int fakeSol1;
    private int fakeSol2;

    private int pos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);


        int1Text = (TextView) findViewById(R.id.int1);
        int2Text = (TextView) findViewById(R.id.int2);
        soluce1 = (Button) findViewById(R.id.soluce1);
        soluce2 = (Button) findViewById(R.id.soluce2);
        soluce3 = (Button) findViewById(R.id.soluce3);


        newEquation();

        // Listeners sur les boutons

        soluce1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code exécuté quand on click sur le bouton
                switch(pos){
                    case 1:
                        // on met le bouton en vert
                        soluce1.setBackgroundColor(GREEN);
                        soluce2.setBackgroundColor(BLUE);
                        soluce3.setBackgroundColor(BLUE);
                        newEquation();
                        break;
                    default : // si pas dans le cas 1
                        soluce1.setBackgroundColor(RED);
                        soluce2.setBackgroundColor(BLUE);
                        soluce3.setBackgroundColor(BLUE);
                        // et on fait vibrer
                        vib();

                }

            }
        });

        soluce2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code exécuté quand on click sur le bouton
                switch(pos){
                    case 2:
                        // on met le bouton en vert
                        soluce2.setBackgroundColor(GREEN);
                        soluce1.setBackgroundColor(BLUE);
                        soluce3.setBackgroundColor(BLUE);
                        newEquation();
                        break;
                    default : // si pas dans le cas 1
                        soluce2.setBackgroundColor(RED);
                        soluce1.setBackgroundColor(BLUE);
                        soluce3.setBackgroundColor(BLUE);
                        // et on fait vibrer
                        vib();

                }

            }
        });


        soluce3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code exécuté quand on click sur le bouton

                switch(pos){
                    case 3:
                        // on met le bouton en vert
                        soluce3.setBackgroundColor(GREEN);
                        soluce2.setBackgroundColor(BLUE);
                        soluce1.setBackgroundColor(BLUE);
                        newEquation();
                        break;
                    default : // si pas dans le cas 1
                        soluce3.setBackgroundColor(RED);
                        soluce2.setBackgroundColor(BLUE);
                        soluce1.setBackgroundColor(BLUE);
                        // et on fait vibrer
                        vib();

                }

            }
        });

    } // on create


    private void newEquation(){

        soluce1.setBackgroundColor(BLUE);
        soluce2.setBackgroundColor(BLUE);
        soluce3.setBackgroundColor(BLUE);

        rdm1 = new Random().nextInt(10) + 1;
        rdm2 = new Random().nextInt(10) + 1;
        soluce=rdm1+rdm2;

        int1Text.setText(Integer.toString(rdm1));
        int2Text.setText(Integer.toString(rdm2));



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
            soluce1.setText(Integer.toString(soluce));
            soluce2.setText(Integer.toString(fakeSol1));
            soluce3.setText(Integer.toString(fakeSol2));

        }
        else if (pos==2){
            soluce1.setText(Integer.toString(fakeSol1));
            soluce2.setText(Integer.toString(soluce));
            soluce3.setText(Integer.toString(fakeSol2));

        }
        else if (pos==3){
            soluce1.setText(Integer.toString(fakeSol2));
            soluce2.setText(Integer.toString(fakeSol1));
            soluce3.setText(Integer.toString(soluce));
        }
    } // new Equation

    private void vib() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }
} // class