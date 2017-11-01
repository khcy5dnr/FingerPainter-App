package com.example.durai23.fingerpainterapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SetColourActivity extends AppCompatActivity {

    static final int SETCOLOUR_ACTIVITY_REQUEST_CODE = 1;

    Button redColour;
    Button yellowColour;
    Button blueColour;
    Button blackColour;
    Button greenColour;
    Button magentaColour;
    Button grayColour;
    Button whiteColour;

    private int colourChoice = 0;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_colour);

        redColour = (Button)findViewById(R.id.redButton);
        yellowColour = (Button)findViewById(R.id.yellowButton);
        blueColour = (Button)findViewById(R.id.blueButton);
        blackColour = (Button)findViewById(R.id.blackButton);
        greenColour = (Button)findViewById(R.id.greenButton);
        magentaColour = (Button)findViewById(R.id.magentaButton);
        grayColour = (Button)findViewById(R.id.grayButton);
        whiteColour = (Button)findViewById(R.id.whiteButton);

        redColour.setBackgroundColor(Color.RED);
        yellowColour.setBackgroundColor(Color.YELLOW);
        blueColour.setBackgroundColor(Color.BLUE);
        blackColour.setBackgroundColor(Color.BLACK);
        greenColour.setBackgroundColor(Color.GREEN);
        magentaColour.setBackgroundColor(Color.MAGENTA);
        grayColour.setBackgroundColor(Color.GRAY);
        whiteColour.setBackgroundColor(Color.WHITE);

        redColour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                colourChoice = 1;
                return_colourChoice();
            }
        });

        yellowColour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                colourChoice = 2;
                return_colourChoice();
            }
        });

        blueColour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                colourChoice = 3;
                return_colourChoice();
            }
        });

        blackColour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                colourChoice = 4;
                return_colourChoice();
            }
        });

        greenColour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                colourChoice = 5;
                return_colourChoice();
            }
        });

        magentaColour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                colourChoice = 6;
                return_colourChoice();
            }
        });

        grayColour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                colourChoice = 7;
                return_colourChoice();
            }
        });

        whiteColour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                colourChoice = 8;
                return_colourChoice();
            }
        });
    }

    public void return_colourChoice(){
        bundle.putInt("colourChoice",colourChoice);
        Intent result = new Intent();
        result.putExtras(bundle);
        setResult(SETCOLOUR_ACTIVITY_REQUEST_CODE, result);
        finish();// destroy the activity after the colour is chosen.
    }

}
