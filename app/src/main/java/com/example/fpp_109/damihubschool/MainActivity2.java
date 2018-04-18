package com.example.fpp_109.damihubschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;




public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread nTh = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2200);
                    Intent intent = new Intent(getApplicationContext(),menu.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        nTh.start();
    }


}
