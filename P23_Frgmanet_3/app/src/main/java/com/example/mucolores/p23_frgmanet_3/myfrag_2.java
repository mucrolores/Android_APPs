package com.example.mucolores.p23_frgmanet_3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class myfrag_2 extends Fragment {

    private TextView receiveText;

    public myfrag_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myfrag_2,container,false);

        receiveText = view.findViewById(R.id.textView);

        return view;
    }

    public void setText(String inputString)
    {
        receiveText.setText(inputString);
    }

}
