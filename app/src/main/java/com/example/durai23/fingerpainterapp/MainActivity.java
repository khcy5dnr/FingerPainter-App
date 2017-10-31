package com.example.durai23.fingerpainterapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    static final int SETCOLOUR_ACTIVITY_REQUEST_CODE = 1;
    static final int SET_BRUSH_WIDTH_ACTIVITY_REQUEST_CODE = 2;
    private FingerPainterView fingerPainterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerPainterView = (FingerPainterView) findViewById(R.id.canvasView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETCOLOUR_ACTIVITY_REQUEST_CODE){
            Bundle bundle = data.getExtras();
            int choice = bundle.getInt("colourChoice");//get colour code
            Log.d("Colour selection","Main Activity ok" + choice);
            changeColour(choice);
        }
    }

    public void setColour(View v){
        Intent intent = new Intent(MainActivity.this, SetColourActivity.class);
        startActivityForResult(intent,SETCOLOUR_ACTIVITY_REQUEST_CODE);
    }

    public void changeColour(int num){
        switch (num){
            case 1:fingerPainterView.setColour(Color.RED);break;
            case 2:fingerPainterView.setColour(Color.YELLOW);break;
            case 3:fingerPainterView.setColour(Color.BLUE);break;
            case 4:fingerPainterView.setColour(Color.BLACK);break;
            case 5:fingerPainterView.setColour(Color.GREEN);break;
            case 6:fingerPainterView.setColour(Color.MAGENTA);break;
            case 7:fingerPainterView.setColour(Color.GRAY);break;
            case 8:fingerPainterView.setColour(Color.WHITE);break;
            default:
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
    }

    public void setBrushWidth(View v){
        Intent intent = new Intent(MainActivity.this, SetBrushSize_Activity.class);
        startActivityForResult(intent,SET_BRUSH_WIDTH_ACTIVITY_REQUEST_CODE);
    }
}
