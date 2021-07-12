package com.example.mucolores.p23_frgmanet_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements myfrag_1.myFragment_1Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void getInputString(String inputs) {
        myfrag_2 frag2 = (myfrag_2) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        frag2.setText(inputs);
    }
}
