package com.ocv.testproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

public class DetailsActivty extends AppCompatActivity {

    Context context;
    TextView title, time, description;
    Model passedModel;
    ImageView img;
    String imageURl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activty);
        context = this;

        title = (TextView)findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        img = (ImageView) findViewById(R.id.img);
        description = (TextView) findViewById(R.id. description);

        passedModel = (Model) getIntent().getExtras().getSerializable("data");

        title.setText(passedModel.getTitle());
        time.setText(Utils.getDateFromSecs(passedModel.getDate_sec()));

        try {
            JSONArray images = new JSONArray(passedModel.getImages());
            if(images.length() > 0){
                img.setVisibility(View.VISIBLE);

                imageURl = images.getJSONObject(0).getString("large");

                Picasso.with(context).load(imageURl).into(img);

            }else{
                img.setVisibility(View.GONE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullImageActivity.class);
                intent.putExtra("img_url", imageURl);
                startActivity(intent);
            }
        });

        description.setText(Html.fromHtml(passedModel.getContent()));


    }
}
