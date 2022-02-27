package com.example.youtubeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class More extends AppCompatActivity {
    CardView account, library, history, help, feedback, logout;
    TextView edit_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        account = findViewById(R.id.c_account);
        library = findViewById(R.id.c_library);
        history = findViewById(R.id.c_history);
        help = findViewById(R.id.c_help);
        feedback = findViewById(R.id.c_feedback);
        logout = findViewById(R.id.c_logout);

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                more_return(view,"1");
            }
        });

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                more_return(view,"2");
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                more_return(view,"3");
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                more_return(view,"4");
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                more_return(view,"5");
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login(view);
            }
        });
        //initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.more);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext()
                                ,dashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext()
                                ,about.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.more:
                        return true;
                }

                return false;
            }
        });

    }

    public void more_return(View v, String x){
        String s = "";
        edit_text = findViewById(R.id.textView2);
        try{
            URL url = new URL("https://fjrmobileprog.000webhostapp.com/more_return.php?a=" + x);
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            int data = isr.read();
            while(data != -1){
                char current = (char) data;
                s = s + current;
                data = isr.read();
            }
            edit_text.setText(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Login(View v) {
        Intent intent = new Intent(this, Yt_Login.class);
        startActivity(intent);
    }
}