package com.example.youtubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    String sendurl = "https://fjrmobileprog.000webhostapp.com/signUp.php";
    RequestQueue req;
    int success;
    String TAG_Success = "success";
    String TAG_Message = "message";
    String tag_json_obj = "json_obj_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt_signup);

        register = findViewById(R.id.signup);
        username = findViewById(R.id.sign_username);
        password = findViewById(R.id.sign_password);

        req = Volley.newRequestQueue(getApplicationContext());
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
        StringRequest request = new StringRequest(Request.Method.POST,
                sendurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                        } catch (JSONException e) {
                            Toast.makeText(yt_signup.this, "Error: " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(yt_signup.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            public Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("username",username.getText().toString());
                params.put("password",password.getText().toString());
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10000,1,1.0f));
        req.add(request);
    }
}