package com.example.mucolores.p13_my_fragment_2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class showing_fragment extends Fragment {

    private TextView numberText;

    public showing_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_showing_fragment, container, false);

        numberText = (TextView)view.findViewById(R.id.numberText);

        return view;
    }

    public void modifyNumber(int targetNum)
    {
        numberText.setText(String.valueOf(targetNum));
    }

}
