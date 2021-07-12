package com.example.mucolores.p21_viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.viewPager1);
        mViewPager.setAdapter(mSectionsAdapter);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment myFrag = new Fragment();
            switch(position)
            {
                case 0:
                    myFrag = new myfrag_1();
                    break;
                case 1:
                    myFrag = new myfrag_2();
                    break;
                case 2:
                    myFrag = new myfrag_3();
                    break;
                case 3:
                    myFrag = new myfrag_4();
                    break;
            }
            return myFrag;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position)
            {
                case 0:
                    return "Fragment_1";
                case 1:
                    return "Fragment_2";
                case 2:
                    return "Fragment_3";
                case 3:
                    return "Fragment_4";
                default:
                    return null;
            }
        }
    }


}
