package com.example.projetandroid_picart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static com.example.projetandroid_picart.SMS_Receiver.msg;


public class MultiActivity extends AppCompatActivity{


    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0;

    private static TextView int1Text;
    private static TextView int2Text;
    private static TextView opText;
    private static TextView soluceText;
    private static TextView soluceTextHelp;


    private static Button soluce1;
    private static Button soluce2;
    private static Button soluce3;

    private static int x1;
    private static int x2;

    private static int pos;

    private static int soluce;
    private static int fakeSol1;
    private static int fakeSol2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS )){

            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }


        //equation

        int1Text = (TextView) findViewById(R.id.int1);
        int2Text = (TextView) findViewById(R.id.int2);
        opText = (TextView) findViewById(R.id.opText);
        soluceText = (TextView) findViewById(R.id.soluceText);
        soluceTextHelp = (TextView) findViewById(R.id.soluceTextHelp);


        soluce1 = (Button) findViewById(R.id.soluce1);
        soluce2 = (Button) findViewById(R.id.soluce2);
        soluce3 = (Button) findViewById(R.id.soluce3);

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
                        initialState();
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
                        initialState();
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
                        initialState();
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



    } // onCreate



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch(requestCode)
        {
            case MY_PERMISSIONS_REQUEST_RECEIVE_SMS:
            {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Thank you for permitting !", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, "Need permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    private void initialState(){
        soluce1.setBackgroundColor(BLUE);
        soluce2.setBackgroundColor(BLUE);
        soluce3.setBackgroundColor(BLUE);
        soluceText.setText("En attente d'une équation...");
        opText.setTextColor(WHITE);
        soluceTextHelp.setTextColor(BLACK);
        int1Text.setText(" ");
        int2Text.setText(" ");
        soluce1.setText("?");
        soluce2.setText("?");
        soluce3.setText("?");
    }

    //@Override
    public static void  initEquation(String sms) {
        String[] equation = sms.split(" ");
        soluceText.setText("Quelle est la solution ?");
        opText.setTextColor(BLACK);
        soluceTextHelp.setTextColor(WHITE);
        x1=Integer.parseInt(equation[0]);
        int1Text.setText(equation[0]);
        x2=Integer.parseInt(equation[1]);
        int2Text.setText(equation[1]);

        soluce = x1 + x2;

        fakeSol1 = new Random().nextInt(10) + 1;
        fakeSol2 = new Random().nextInt(10) + 1;

        while(fakeSol1==soluce){
            fakeSol1 = new Random().nextInt(20) + 1;
        }
        while(fakeSol2==soluce || fakeSol2==fakeSol1){
            fakeSol2 = new Random().nextInt(20) + 1;
        }

        pos = new Random().nextInt(3) + 1;

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





    } // init equation


    private void vib() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }
}