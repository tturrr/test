package com.example.user.duty;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class intro_Activity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView percent;
    int i;
    Handler handler;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        percent = (TextView) findViewById(R.id.percent_view);


        handler = new Handler();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (i = 0; i <= 99; i++) {
                    try {
                        thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.setProgress(i);
                    if(i==99){
                        Intent intent = new Intent(intro_Activity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            percent.setText(i + "%");
                        }
                    });
                }
            }
        });
        thread.start();

    }

}
