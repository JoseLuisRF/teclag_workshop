package com.apps.fourplex.tabletop;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.apps.fourplex.tabletop.ui.FloorFragment;
import com.parse.ParseQuery;


import java.util.Random;


/**
 * Created by joseluisrf on 6/26/15.
 */
public class WcActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    public static final int NUM_PAGES = 5;
    private PagerAdapter mPagerAdapter;
    private ViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wc_activity);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        Random random;
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            random = new Random();
        }

        @Override
        public Fragment getItem(int position) {
            FloorFragment floorFragment= new FloorFragment();
            Bundle b = new Bundle();
            switch (position){
                case 0:
                    b.putInt(FloorFragment.FLOOR_LEVEL, FloorFragment.FLOOR_3);
                    floorFragment.setArguments(b);
                    return floorFragment;
                case 1:
                    b.putInt(FloorFragment.FLOOR_LEVEL, FloorFragment.FLOOR_4);
                    floorFragment.setArguments(b);
                    return floorFragment;
                case 2:
                    b.putInt(FloorFragment.FLOOR_LEVEL, FloorFragment.FLOOR_5);
                    floorFragment.setArguments(b);
                    return floorFragment;
                case 3:
                    b.putInt(FloorFragment.FLOOR_LEVEL, FloorFragment.FLOOR_6);
                    floorFragment.setArguments(b);
                    return floorFragment;
                case 4:
                    b.putInt(FloorFragment.FLOOR_LEVEL, FloorFragment.FLOOR_7);
                    floorFragment.setArguments(b);
                    return floorFragment;
                default:
                    b.putInt(FloorFragment.FLOOR_LEVEL, 1);
                    floorFragment.setArguments(b);
                    return floorFragment;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private void updateBathrooms(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }
}
