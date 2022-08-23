 package com.example.freshmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.freshmart.activities.MainActivity2;

import static java.lang.Thread.sleep;

public class wlcmscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlcmscreen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread td = new Thread(){
            public void run()
            {
                try
                {

                    sleep(5000);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(wlcmscreen.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        };td.start();



    }
}