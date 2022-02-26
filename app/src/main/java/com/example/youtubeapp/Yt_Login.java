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
import java.net.URL;
import java.net.URLConnection;

public class Yt_Login extends AppCompatActivity {
    Button home;
    EditText username, password, txtTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt_login);

        home = findViewById(R.id.signup);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((username.getText().toString().equals("FJR")) && (password.getText().toString().equals("admin"))){
                    Home(view);
                }
                else if((username.getText().toString().matches("")) && (password.getText().toString().matches(""))){
                    Toast.makeText(Yt_Login.this, "Please Fill The Information Above", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Yt_Login.this, "Incorrect Username/Password, Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Home(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Register(View v) {
        Intent intent = new Intent(this, yt_signup.class);
        startActivity(intent);
    }
}