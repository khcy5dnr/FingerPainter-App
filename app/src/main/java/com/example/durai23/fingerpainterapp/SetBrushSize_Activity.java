package com.example.durai23.fingerpainterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class SetBrushSize_Activity extends AppCompatActivity {
    static final int SET_BRUSH_WIDTH_ACTIVITY_REQUEST_CODE = 2;

    SeekBar seekBar_width;
    TextView brushWidth;
    Button okButton;
    RadioButton roundButton;
    private int size_brushWidth = 20;

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_brush_size_);

        seekBar_width = (SeekBar)findViewById(R.id.seekBar_width);
        brushWidth = (TextView) findViewById(R.id.brush_width);
        okButton = (Button)findViewById(R.id.okButton);
        roundButton = (RadioButton)findViewById(R.id.radioButton_Round);


        seekBar_width.setMax(200);

        seekBar_width.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0){
                    progressValue = progress+1;
                }
                else{
                    progressValue = progress;
                }
                brushWidth.setText(Integer.toString(progressValue));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                brushWidth.setText(Integer.toString(progressValue));
                size_brushWidth = progressValue;
            }
        });



        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                return_brushSize();
            }
        });
    }

    public void return_brushSize(){
        bundle.putInt("brushWidth",size_brushWidth);
        Intent result = new Intent();
        result.putExtras(bundle);
        setResult(SET_BRUSH_WIDTH_ACTIVITY_REQUEST_CODE, result);
        finish();
    }
}
