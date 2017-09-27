package com.ocv.testproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by lancelittle on 9/24/17.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<Model> items;
    Context mContext;

    public MainAdapter(Context context, ArrayList<Model> items) {
        this.items = items;
        mContext = context;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, final int position) {
        holder.mTitle.setText(items.get(position).getTitle());
        holder.mDate.setText(Utils.getDateFromSecs(items.get(position).getDate_sec()));
        holder.mDescription.setText(Html.fromHtml(items.get(position).getContent()));

        try {
            JSONArray images = new JSONArray(items.get(position).getImages());
            if(images.length() > 0){
                holder.mImage.setVisibility(View.VISIBLE);

                String imageURl = images.getJSONObject(0).getString("large");

                Picasso.with(mContext).load(imageURl).into(holder.mImage);

            }else{
                holder.mImage.setVisibility(View.GONE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        holder.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsActivty.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", items.get(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                //Toast.makeText(mContext, "Position clicked: "+position, Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle, mDate, mDescription;
        public ImageView mImage;
        public LinearLayout body;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mDescription = (TextView) itemView.findViewById(R.id.decription);
            mImage = (ImageView) itemView.findViewById(R.id.image);
            body = (LinearLayout) itemView.findViewById(R.id.body);
        }
    }
}
