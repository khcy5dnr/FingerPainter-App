package com.example.durai23.fingerpainterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SetBrushSize_Activity extends AppCompatActivity {
    static final int SET_BRUSH_WIDTH_ACTIVITY_REQUEST_CODE = 2;

    SeekBar seekBar_width;
    TextView brushWidth;
    Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_brush_size_);

        seekBar_width = (SeekBar)findViewById(R.id.seekBar_width);
        brushWidth = (TextView) findViewById(R.id.brush_width);
        okButton = (Button)findViewById(R.id.okButton);

        seekBar_width.setMax(150);
        seekBar_width.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                brushWidth.setText(Integer.toString(progressValue));
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                /*Toast.makeText(SetBrushSize_Activity.this, "Seek bar progress is :" + progressValue,
                        Toast.LENGTH_SHORT).show();*/
                brushWidth.setText(Integer.toString(progressValue));
            }
        });
    }
}
