package com.example.mucolores.p13_my_fragment_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements control_fragment.callBackInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//因為前面有implement controlFragment的callbackInterface 所以那個callback裡面有寫好一個modNumber這裡會需要定義
    @Override
    public void modNumber(int number) {
        showing_fragment tmpfragment = (showing_fragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        tmpfragment.modifyNumber(number);
    }
}
