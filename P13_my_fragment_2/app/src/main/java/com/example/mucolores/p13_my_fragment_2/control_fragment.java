package com.example.mucolores.p13_my_fragment_2;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.String.valueOf;


/**
 * A simple {@link Fragment} subclass.
 */
public class control_fragment extends Fragment {


    public control_fragment() {
        // Required empty public constructor
    }

    private static EditText plusRate;
    private static Button plusBtn,minusBtn;
    private int number = 0;

    callBackInterface acticityCommander;

    public interface callBackInterface
    {
        public void modNumber(int number);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            acticityCommander = (callBackInterface)activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString());
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_control_fragment, container, false);

        plusRate = (EditText)view.findViewById(R.id.timeRate);
        plusBtn = (Button)view.findViewById(R.id.plusBtn);
        minusBtn = (Button)view.findViewById(R.id.minusBtn);

        plusBtn.setOnClickListener(plusBtnOCL);
        minusBtn.setOnClickListener(minusBtnOCL);

        return view;
    }

    public View.OnClickListener plusBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            number += Integer.parseInt(plusRate.getText().toString());
            acticityCommander.modNumber(number);
        }
    };

    public View.OnClickListener minusBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            number -= Integer.parseInt(plusRate.getText().toString());
            acticityCommander.modNumber(number);
        }
    };



}
