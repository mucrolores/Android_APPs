package com.example.mucolores.image_project_2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory{

    private GridView mGridView;
    private ImageSwitcher mImgSwitcher;

    private Integer[] miImgArr={
            R.mipmap.screenpost_1,R.mipmap.screenpost_2,
            R.mipmap.screenpost_3,R.mipmap.screenpost_4,
            R.mipmap.screenpost_5};

    private Integer[] miThumbImgArr={
            R.mipmap.screenpost_1,R.mipmap.screenpost_2,
            R.mipmap.screenpost_3,R.mipmap.screenpost_4,
            R.mipmap.screenpost_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgSwitcher = findViewById(R.id.ImageSwitcher1);

        mImgSwitcher.setFactory(this);
        mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
        mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));

        ImageAdapter imgAdap = new ImageAdapter(this,miThumbImgArr);

        mGridView = (GridView)findViewById(R.id.GridView1);
        mGridView.setAdapter(imgAdap);
        mGridView.setOnItemClickListener(gridViewOCL);

    }

    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.WHITE);
        return v;
    }

    private AdapterView.OnItemClickListener gridViewOCL = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mImgSwitcher.setImageResource(miImgArr[position]);

        }
    };

}