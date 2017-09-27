package com.ocv.testproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String PREFS_NAME = "Project1";
    private static final String PREF_DARK_THEME = "Project_theme";

    ArrayList<Model> items = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    Context context;
    Switch toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        context = this;
        toggle = (Switch)findViewById(R.id.switch1);

        // Use the chosen theme
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Base_Dark);
        }else{
            setTheme(R.style.AppTheme_Base_Light);
        }

        toggle.setChecked(useDarkTheme);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                toggleTheme(b);
            }
        });

        //region AsyncTask
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                try{
                    items = Parser.parse();
                }catch (IOException e){
                    e.printStackTrace();
                } catch (JSONException f){
                    f.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                setupList();
            }
        }.execute();
        //endregion

    }

    private void toggleTheme(boolean b) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, b);
        editor.apply();

        Intent intent = getIntent();
        finish();

        startActivity(intent);
    }

    private void setupList(){
        //TODO Code used to attach list to RecyclerView should go here
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MainAdapter(context,items);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}
