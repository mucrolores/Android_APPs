package com.example.mucolores.p16_scrollable_swipe_and_action_bar;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import static com.example.mucolores.p16_scrollable_swipe_and_action_bar.R.menu.menu;

public class MainActivity extends AppCompatActivity {


    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.Activity_action_bar);
        setSupportActionBar(mToolbar);

    }
//Action bar 三個點的那個設定程式碼
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.my_menu_item:
                Toast.makeText(MainActivity.this,R.string.menu_item_string1,Toast.LENGTH_LONG).show();
                break;
            case R.id.help:
                Toast.makeText(MainActivity.this,R.string.menu_item_string2,Toast.LENGTH_LONG).show();
                break;
            case R.id.settings:
                Intent set_intent = new Intent(this,Setting_activity.class);
                startActivity(set_intent);
                break;


            default:
                Toast.makeText(MainActivity.this,R.string.default_string,Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private class SectionsPagerAdapter extends FragmentPagerAdapter
    {
        public SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            switch (position)
            {
                case 0:
                    fragment = new FirstFragment();
                    break;
                case 1:
                    fragment = new SecondFragment();
                    break;
                case 2:
                    fragment = new ThirdFragment();

                    break;
                case 3:
                    fragment = new ForthFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount()
        {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return "第1個fragment";
                case 1:
                    return "第2個fragment";
                case 2:
                    return "第3個fragment";
                case 3:
                    return "第4個fragment";
                default:
                    return null;
            }
        }
    }
}
