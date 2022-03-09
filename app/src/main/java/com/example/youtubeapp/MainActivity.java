package com.example.youtubeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    EditText txtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        txtTest = findViewById(R.id.edit);
        txtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Change(view);
            }
        });

        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.dashboard:
                                startActivity(new Intent(getApplicationContext()
                                        ,dashboard.class));
                                overridePendingTransition(0,0);
                                return true;
                            case R.id.home:
                                return true;
                            case R.id.about:
                                startActivity(new Intent(getApplicationContext()
                                        ,about.class));
                                overridePendingTransition(0,0);
                                return true;
                            case R.id.more:
                                startActivity(new Intent(getApplicationContext()
                                        ,More.class));
                                overridePendingTransition(0,0);
                                return true;
                        }

                        return false;
                    }
                });
    }
    public void openVideo(View v) {
        Intent intent = new Intent(this, VideoViewActivity.class);
        startActivity(intent);
    }
    public void Change(View v){
        String s = "";
        try{
            URL url = new URL("https://fjrmobileprog.000webhostapp.com/search.php?a=" + txtTest.getText().toString());
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            int data = isr.read();
            while(data != -1){
                char current = (char) data;
                s = s + current;
                data = isr.read();
            }
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}