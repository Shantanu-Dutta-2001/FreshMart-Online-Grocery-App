package com.example.freshmart.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.freshmart.MainActivity;
import com.example.freshmart.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    ProgressBar progressbar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        auth=FirebaseAuth.getInstance();
        progressbar=findViewById(R.id.progressbar);
        progressbar.setVisibility(View.GONE);

        if(auth.getCurrentUser() != null)
        {
            progressbar.setVisibility(View.VISIBLE);
            startActivity(new Intent(MainActivity2.this, MainActivity.class));
            Toast.makeText(this, "Please wait you are already logged in", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void login(View view)
    {
        startActivity(new Intent(MainActivity2.this, LoginActivity.class));

    }

    public void registration(View view)
    {
        startActivity(new Intent(MainActivity2.this, RegistrationActivity.class));

    }
}