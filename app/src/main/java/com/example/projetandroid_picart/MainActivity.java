package com.example.projetandroid_picart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button soloClicButton;
    private Button soloGyroButton;
    //private Button soloLightButton;
    private Button multiClicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soloClicButton = (Button) findViewById(R.id.soloClicButton);
        soloGyroButton = (Button) findViewById(R.id.soloGyroButton);
        //soloLightButton = (Button) findViewById(R.id.soloLightButton);
        multiClicButton = (Button) findViewById(R.id.multiClicButton);

        soloClicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code exécuté quand on clicke sur le bouton

                //On commence par créer notre intent
                Intent soloClickActivity = new Intent(MainActivity.this, SoloClickActivity.class);

                // et on lance l'activité correspondante
                startActivity(soloClickActivity);
            }
        });

        soloGyroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code exécuté quand on clicke sur le bouton

                //On commence par créer notre intent
                Intent soloGyroActivity = new Intent(MainActivity.this, SoloGyroActivity.class);

                // et on lance l'activité correspondante
                startActivity(soloGyroActivity);
            }
        });

        multiClicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code exécuté quand on clicke sur le bouton

                //On commence par créer notre intent
                Intent multiActivity = new Intent(MainActivity.this, MultiActivity.class);

                // et on lance l'activité correspondante
                startActivity(multiActivity);
            }
        });
    }


}