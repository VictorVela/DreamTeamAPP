package com.example.dreamteam.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamteam.R;
import com.example.dreamteam.activity.PessoaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {


    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Person");

        View  myView  = inflater.inflate(R.layout.fragment_person,container,false);

        chamarTelaPessoa(myView);

        return myView;
    }


    private void chamarTelaPessoa(View view) {
        Intent intent = new Intent(getActivity(), PessoaActivity.class);
        startActivity(intent);
    }

}
