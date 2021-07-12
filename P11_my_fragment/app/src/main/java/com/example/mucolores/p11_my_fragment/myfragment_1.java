package com.example.mucolores.p11_my_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class myfragment_1 extends Fragment
{

    public myfragment_1()
    {
        // Required empty public constructor
    }

    //basic set of editText
    private static EditText topInput,bottomInput;


    myfragment_1Listener activityCommander;

    //interface是一個Activity callback的方法
    public interface myfragment_1Listener
    {
        public void modifyText(String String1,String String2);
    }

//如果這個fragment是onAttach的狀態下 會需要有以下的initial設定等等的
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            activityCommander = (myfragment_1Listener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString());
        }
    }

    //基本的初始onCreateView設定
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_myfragment_1, container, false);

        topInput = (EditText) view.findViewById(R.id.typeText1);
        bottomInput = (EditText) view.findViewById(R.id.typeText2);
        final Button setbutton = (Button) view.findViewById(R.id.setbutton);

        setbutton.setOnClickListener(btnOCL);

        return view;
    }

//設定一個btnOCL如果按鈕按下 就執行activityCommander 控制activity然後執行modifytext(這裡會引入兩個參數) activity會設定好兩參數要幹嘛
    private View.OnClickListener btnOCL = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            activityCommander.modifyText(topInput.getText().toString(),bottomInput.getText().toString());
        }
    };


}
