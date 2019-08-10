package com.vibhusharma.notificationdemo02;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mProfileLabel;
    private TextView mUsersLabel;
    private TextView mNotificationLabel;

    private ViewPager mMainPager;
    private  PagerViewAdapter mPagerViewAdapter;
private FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser  = mAuth.getCurrentUser();


        if(currentUser==null)
        {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           mAuth = FirebaseAuth.getInstance();

        mProfileLabel = (TextView)findViewById(R.id.profileLabel);

        mProfileLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMainPager.setCurrentItem(0);

            }
        });

        mUsersLabel = (TextView)findViewById(R.id.usersLabel);

        mUsersLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
mMainPager.setCurrentItem(1);

            }
        });

        mNotificationLabel = (TextView)findViewById(R.id.notifiationsLabel);

        mNotificationLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMainPager.setCurrentItem(2);


            }
        });
        mProfileLabel.setTextSize(22);
        mProfileLabel.setTextColor(Color.RED);

        mUsersLabel.setTextSize(15);
        mUsersLabel.setTextColor(Color.BLACK);

        mNotificationLabel.setTextSize(15);
        mNotificationLabel.setTextColor(Color.BLACK);

        mMainPager = (ViewPager)findViewById(R.id.mainPager);
        mMainPager.setOffscreenPageLimit(2);
        mPagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        mMainPager.setAdapter(mPagerViewAdapter);

        mMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {



            }

            @Override
            public void onPageSelected(int i) {


changeTabs(i);



            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });




    }

    private void changeTabs(int position)
    {

       if(position==0)
       {
           mProfileLabel.setTextSize(22);
           mProfileLabel.setTextColor(Color.RED);

           mUsersLabel.setTextSize(15);
           mUsersLabel.setTextColor(Color.BLACK);

           mNotificationLabel.setTextSize(15);
           mNotificationLabel.setTextColor(Color.BLACK);





       }

       else if(position==1)
       {

           mProfileLabel.setTextSize(15);
           mProfileLabel.setTextColor(Color.BLACK);

           mUsersLabel.setTextSize(22);
           mUsersLabel.setTextColor(Color.RED);

           mNotificationLabel.setTextSize(15);
           mNotificationLabel.setTextColor(Color.BLACK);





       }
       else if (position==2)
       {
           mProfileLabel.setTextSize(15);
           mProfileLabel.setTextColor(Color.BLACK);

           mUsersLabel.setTextSize(15);
           mUsersLabel.setTextColor(Color.BLACK);

           mNotificationLabel.setTextSize(22);
           mNotificationLabel.setTextColor(Color.RED);





       }





    }





}
