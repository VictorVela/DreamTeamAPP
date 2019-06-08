package com.example.dreamteam.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.dreamteam.MainActivity;
import com.example.dreamteam.R;
import com.example.dreamteam.activity.JobListActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindJobFragment extends Fragment implements View.OnClickListener {

    Button buttonJobList;

    public FindJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Job list");

        View myView = inflater.inflate(R.layout.fragment_find_job, container, false);

        buttonJobList = (Button) myView.findViewById(R.id.buttonJobList);

        buttonJobList.setOnClickListener(this);

        return myView;
    }

    /* Botão para abrir activity de lista de job */
    @Override
    public void onClick(View v){
        Toast.makeText(getActivity(),"Botão funcionando", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity() ,JobListActivity.class);

        startActivity(intent);

    }

}
