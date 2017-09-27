package com.ocv.testproject;

import android.content.Context;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.Toast;

import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

public class FullImageActivity extends AppCompatActivity {

    Context context;
    ZoomageView full_img;
    String imageUrl;
    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        context = this;

        full_img = (ZoomageView) findViewById(R.id.full_img);
        imageUrl = getIntent().getStringExtra("img_url");

        Picasso.with(context).load(imageUrl).into(full_img);

    }


}
