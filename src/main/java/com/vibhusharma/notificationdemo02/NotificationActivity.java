package com.vibhusharma.notificationdemo02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {


    private TextView mNotif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        String dataMessage = getIntent().getStringExtra("message");
        String dataFrom = getIntent().getStringExtra("from_user_id");
        mNotif = findViewById(R.id.myTextView);
        mNotif.setText("Message : "+dataMessage+" From: "+dataFrom);



    }
}
