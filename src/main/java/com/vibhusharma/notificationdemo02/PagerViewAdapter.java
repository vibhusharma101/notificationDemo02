package com.vibhusharma.notificationdemo02;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PagerViewAdapter extends FragmentStatePagerAdapter {


    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position)
        {
            case 0 :
                ProfileFragment profileFragment = new ProfileFragment();

                return profileFragment;

            case 1:
                UsersFragment usersFragment = new UsersFragment();

                return usersFragment;

            case 2 :

                NotificationsFragment notificationsFragment = new NotificationsFragment();

                return notificationsFragment;

                default:
                    return null;


        }















    }

    @Override
    public int getCount() {
        return 3;
    }
}
