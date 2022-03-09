package com.example.youtubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class yt_signup extends AppCompatActivity {
    Button register;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt_signup);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        register = findViewById(R.id.signup);
        username = findViewById(R.id.sign_username);
        password = findViewById(R.id.sign_password);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((username.getText().toString().matches("")) || (password.getText().toString().matches(""))){
                    Toast.makeText(yt_signup.this, "Please Input Proper Information", Toast.LENGTH_SHORT).show();
                }
                else{
                    send_data();
                    Login(view);
                }
            }
        });
    }

    public void Login(View v) {
        Intent intent = new Intent(this, Yt_Login.class);
        startActivity(intent);
    }

    public void send_data(){
        String s = "";
        try{
            URL url = new URL("https://fjrmobileprog.000webhostapp.com/FJR_signup.php?a=" + username.getText().toString() + "&b=" + password.getText().toString());
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            int data = isr.read();
            while(data != -1){
                char current = (char) data;
                s = s + current;
                data = isr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(s.equals("no")){
            Toast.makeText(yt_signup.this, "Error for inserting data, please try again", Toast.LENGTH_SHORT).show();
        }
    }
}