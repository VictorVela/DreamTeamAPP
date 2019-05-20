package com.example.dreamteam.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamteam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Information2Fragment extends Fragment {


    public Information2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Information");
        return inflater.inflate(R.layout.fragment_information2, container, false);
    }

}
