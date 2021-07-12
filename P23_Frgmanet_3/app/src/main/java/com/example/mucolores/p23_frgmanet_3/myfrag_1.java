package com.example.mucolores.p23_frgmanet_3;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class myfrag_1 extends Fragment {

    private EditText medtText;
    private String tmpString;
    private Button setTextButton;

    public myfrag_1() {
        // Required empty public constructor
    }

    myFragment_1Listener activityCommander;

    public interface myFragment_1Listener
    {
        public void getInputString(String inputs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_myfrag_1, container, false);

        medtText = view.findViewById(R.id.inputText);
        tmpString = medtText.getText().toString();
        setTextButton = view.findViewById(R.id.setBtnID);
        setTextButton.setOnClickListener(setTextBtnOCL);

        return view;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            activityCommander = (myFragment_1Listener)activity;
        }
        catch(ClassCastException e)
        {
            throw new ClassCastException(activity.toString());
        }
    }

    View.OnClickListener setTextBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tmpString = medtText.getText().toString();
            activityCommander.getInputString(tmpString);
        }
    };

}
