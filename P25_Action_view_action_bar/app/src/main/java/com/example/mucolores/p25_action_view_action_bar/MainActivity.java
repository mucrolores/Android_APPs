package com.example.mucolores.p25_action_view_action_bar;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar mActBar = getSupportActionBar();
        mActBar.setDisplayUseLogoEnabled(true);

        testButton = findViewById(R.id.button);
        testButton.setOnClickListener(testButtonOCL);

    }

    View.OnClickListener testButtonOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intnent = new Intent();
            intnent.setClass(MainActivity.this,Main2Activity.class);
            startActivity(intnent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.custom_menu,menu);

        ImageButton BlackBtn,WhiteBtn;

        BlackBtn = (ImageButton) menu.findItem(R.id.menu_button_1).getActionView();
        WhiteBtn = (ImageButton) menu.findItem(R.id.menu_button_2).getActionView();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_button_1:
                Toast.makeText(MainActivity.this,"Black Button selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_button_2:
                Toast.makeText(MainActivity.this,"white Button selected",Toast.LENGTH_LONG).show();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
