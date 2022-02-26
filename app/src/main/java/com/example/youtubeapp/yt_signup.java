package com.example.youtubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class yt_signup extends AppCompatActivity {
    Button register;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt_signup);

        register = findViewById(R.id.signup);
        username = findViewById(R.id.sign_username);
        password = findViewById(R.id.sign_password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((username.getText().toString().matches("")) && (password.getText().toString().matches(""))){
                    Toast.makeText(yt_signup.this, "Please Input Proper Information", Toast.LENGTH_SHORT).show();
                }
                else{
                    Login(view);
                }
            }
        });
    }

    public void Login(View v) {
        Intent intent = new Intent(this, Yt_Login.class);
        startActivity(intent);
    }
}