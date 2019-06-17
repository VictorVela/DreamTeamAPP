package com.example.dreamteam.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamteam.R;
import com.example.dreamteam.activity.GalleryActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {


    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Gallery");

        View myView =  inflater.inflate(R.layout.fragment_person,container,false);

        chamarTelaGallery(myView);

        return myView;
    }

    private void chamarTelaGallery(View view){
        Intent intent = new Intent(getActivity(), GalleryActivity.class);
        startActivity(intent);
    }

}
