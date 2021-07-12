package com.example.mucolores.p11_my_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class myFragment_2 extends Fragment {


    private static TextView upperText,lowerText;

    public myFragment_2() {
        // Required empty public constructor
    }

//這裡本來是一些初始設定的程式碼 但是為了一些reference的設定 在return之前先設定好
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_fragment_2,container,false);

        upperText = (TextView)view.findViewById(R.id.displayText1);
        lowerText = (TextView)view.findViewById(R.id.displayText2);

        return view;
    }

    public void settingText(String upperString,String lowerString)
    {
        upperText.setText(upperString);
        lowerText.setText(lowerString);
    }

}
