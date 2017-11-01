package com.example.durai23.fingerpainterapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int SETCOLOUR_ACTIVITY_REQUEST_CODE = 1;
    static final int SET_BRUSH_WIDTH_ACTIVITY_REQUEST_CODE = 2;
    static final int LOAD_IMAGE_ACTIVITY = 3;
    static final int VIEW_INTENT = 4;

    private FingerPainterView fingerPainterView;

    Button clear;
    Button loadImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fingerPainterView = (FingerPainterView) findViewById(R.id.canvasView);

        //check for permission
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

                // Request the permission.
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},LOAD_IMAGE_ACTIVITY);
        }

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if(Intent.ACTION_VIEW.equals(action) && type != null){
            if (type.startsWith("image/"))
                fingerPainterView.load(intent.getData());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOAD_IMAGE_ACTIVITY:
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.clear:
                Toast.makeText(this,"Canvas is cleared",Toast.LENGTH_SHORT).show();
                fingerPainterView.clearCanvas();
                return true;
            case R.id.uploadImage:
                load_UserImage();
                return true;
            case R.id.deleteImage:
                Toast.makeText(this,"Image is removed",Toast.LENGTH_SHORT).show();
                fingerPainterView.deleteImageFromCanvas();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle bundle = data.getExtras();
        switch(requestCode){
            case SETCOLOUR_ACTIVITY_REQUEST_CODE:
                int choice = bundle.getInt("colourChoice");//get colour code
                Log.d("Colour selection","Main Activity ok" + choice);
                changeColour(choice);
                break;
            case SET_BRUSH_WIDTH_ACTIVITY_REQUEST_CODE:
                fingerPainterView.setBrushWidth(bundle.getInt("brushWidth"));
                Log.d("Colour selection","Main Activity ok" + bundle.getInt("brushWidth"));

                String shape = bundle.getString("brushShape");

                if(shape.compareTo("ROUND") == 0){
                    fingerPainterView.setBrush(Paint.Cap.ROUND);
                    Toast.makeText(this,"ROUND SHAPE SELECTED",Toast.LENGTH_SHORT).show();
                }
                else if(shape.compareTo("SQUARE") == 0){
                    fingerPainterView.setBrush(Paint.Cap.SQUARE);
                    Toast.makeText(this,"SQUARE SHAPE SELECTED",Toast.LENGTH_SHORT).show();
                }

                break;
            case LOAD_IMAGE_ACTIVITY:
                if(resultCode == RESULT_OK){
                    fingerPainterView.load(data.getData());
                    fingerPainterView.setImageAsCanvas();
                }
                break;
            default:
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
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
        Bundle mBundle = new Bundle();
        mBundle.putInt("brushWidth",fingerPainterView.getBrushWidth());
        Intent intent = new Intent(MainActivity.this, SetBrushSize_Activity.class);
        intent.putExtras(mBundle);
        startActivityForResult(intent,SET_BRUSH_WIDTH_ACTIVITY_REQUEST_CODE);
    }

    public void clearCanvas(View v){
        fingerPainterView.clearCanvas();
    }

    public void load_UserImage(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,LOAD_IMAGE_ACTIVITY);
    }
}
