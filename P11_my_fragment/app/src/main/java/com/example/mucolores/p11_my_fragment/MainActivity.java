package com.example.mucolores.p11_my_fragment;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements myfragment_1.myfragment_1Listener{//implement(實作)
// 這裡會實作myfragment_1.myfragment_1Listener(一種callbackInterFace)這個物件裡面寫好的指令(||函式) 根據myfragment_1裡面寫好的會有一個modifyText的函式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//implementing myfragment_1.myfragment_1Listener so need to hoaving override this myfragment_1.myfragment_1Listener's class
    //所以這裡要定義關於modifyText的內容
    @Override
    public void modifyText(String String1, String String2) {

        //calling the class of fragment2 to having the object create called lower_fragment(這裡可以自己命名)
        //透過getSupportFragmentManager可以取得fragment2的(內容(?
        myFragment_2 lower_fragment =(myFragment_2) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        //透過剛剛宣告好的物件 可以執行寫好的class(這裡指myFragment_2)的指令settingText
        lower_fragment.settingText(String1,String2);
    }
}
