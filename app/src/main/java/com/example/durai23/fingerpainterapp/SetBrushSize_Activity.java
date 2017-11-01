package com.example.durai23.fingerpainterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SetBrushSize_Activity extends AppCompatActivity {
    static final int SET_BRUSH_WIDTH_ACTIVITY_REQUEST_CODE = 2;

    SeekBar seekBar_width;
    TextView brushWidth;
    Button okButton;
    RadioGroup radio_ShapeGroup;
    RadioButton radio_ShapeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_brush_size_);

        Bundle mBundle = getIntent().getExtras();

        seekBar_width = (SeekBar)findViewById(R.id.seekBar_width);
        brushWidth = (TextView) findViewById(R.id.brush_width);
        okButton = (Button)findViewById(R.id.okButton);
        radio_ShapeGroup = (RadioGroup) findViewById(R.id.radioGroup);

        brushWidth.setText(Integer.toString(mBundle.getInt("brushWidth")));
        seekBar_width.setMax(200);
        seekBar_width.setProgress(mBundle.getInt("brushWidth"));
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
            }
        });



        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int checkedID = radio_ShapeGroup.getCheckedRadioButtonId();
                radio_ShapeButton = (RadioButton)findViewById(checkedID);
                radio_ShapeButton.setChecked(true);
                return_brushSizeAndShape((String)radio_ShapeButton.getText());
            }
        });
    }

    public void return_brushSizeAndShape(String shapeName){
        Bundle bundle = new Bundle();
        bundle.putInt("brushWidth",Integer.parseInt(brushWidth.getText().toString()));
        bundle.putString("brushShape",shapeName);
        Intent result = new Intent();
        result.putExtras(bundle);
        setResult(SET_BRUSH_WIDTH_ACTIVITY_REQUEST_CODE, result);
        finish();// destroy activity once the brush size or shape is selected.
    }
}
