package com.example.mucolores.db_2019;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private Button button1,button2;
    private ListView listView;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button1 = findViewById(R.id.button1_ID);
        button2 = findViewById(R.id.button2_ID);
        listView = findViewById(R.id.listView_ID);
        button1.setOnClickListener(button1OCL);
        button2.setOnClickListener(button2OCL);

        try{
            SQLiteOpenHelper sqLiteOpenHelper = new DataBaseHelper(Main2Activity.this);
            sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("Demo",new String[]{"_id","name","description"},null,null,null,null,null);
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(Main2Activity.this,R.layout.listview1_layout,cursor,new String[]{"name","description"},new int[]{R.id.listview1_layoutTextView1,R.id.listview1_layoutTextView2},0);
            listView.setAdapter(cursorAdapter);
        }catch (SQLException s)
        {
            Toast.makeText(this, "SQLite Error", Toast.LENGTH_SHORT).show();
        }

    }

    View.OnClickListener button1OCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener button2OCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Main2Activity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
