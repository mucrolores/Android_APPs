package com.example.mucolores.p15_taipei_wait_bus;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;


public class Taiwan_goodgo_activity extends AppCompatActivity {

    private SectionPagerAdapter TaiwanSectionAdapter;
    ViewPager TaiwanViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taiwan_goodgo_activity);

        TaiwanSectionAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        TaiwanViewPager = (ViewPager)findViewById(R.id.TaiWan_viewpager);
        TaiwanViewPager.setAdapter(TaiwanSectionAdapter);

        TabLayout tabLayout = findViewById(R.id.Taiwan_tabLayout);
        tabLayout.setupWithViewPager(TaiwanViewPager);

    }

    private class SectionPagerAdapter extends FragmentPagerAdapter {
        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch(position){
                case 0:
                    fragment = new Taiwan_north_fragment();
                    break;
                case 1:
                    fragment = new Taiwan_east_fragment();
                    break;
                case 2:
                    fragment = new Taiwan_south_fragment();
                    break;
                case 3:
                    fragment = new Taiwan_west_fragment();
                    break;
                case 4:
                    fragment = new Taiwan_Island_fragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position)
            {
                case 0:
                    return "北部";
                case 1:
                    return "東部";
                case 2:
                    return "南部";
                case 3:
                    return "西部";
                case 4:
                    return "離島";
            }
            return super.getPageTitle(position);
        }
    }
}
