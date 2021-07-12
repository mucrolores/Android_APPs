package com.example.mucolores.pss_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {


    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();

        PSSFragment frag = (PSSFragment) getFragmentManager().findFragmentById(R.id.fragMain);

        frag.gameCountText = (TextView) getActivity().findViewById(R.id.totalCountID);
        frag.playerWinText = (TextView) getActivity().findViewById(R.id.winCountID);
        frag.evenText = (TextView) getActivity().findViewById(R.id.looseCountID);
        frag.computerWinText = (TextView) getActivity().findViewById(R.id.evenCountID);

        getActivity().findViewById(R.id.frameLay).setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause(){
        super.onPause();
        getActivity().findViewById(R.id.frameLay).setVisibility(View.GONE);
    }

}
