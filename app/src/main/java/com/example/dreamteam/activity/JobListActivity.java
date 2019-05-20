package com.example.dreamteam.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dreamteam.R;
import com.example.dreamteam.model.Job;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobListActivity extends AppCompatActivity {

    private ListView listJob;
    private ListView jobListaDefinitiva;
    private ArrayList<Job> jobArray = new ArrayList<>();

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        DatabaseReference jobs = reference.child("jobs");

        //DatabaseReference jobsPesquisa = jobs.child("-LfFnv8vs6x2Fian5G2r");

        //Por meio de pesquisa direto na base
        //Query jobsPesquisa = jobs.orderByChild("nome").equalTo("teste job003");

        //limitar pesquisa ao numero determinado
        //Query jobsPesquisa = jobs.orderByKey().limitToFirst(2);

        //pesquisa com todos os jobs
        Query jobsPesquisa = jobs.orderByChild("nome");

        jobListaDefinitiva = (ListView) findViewById(R.id.listJob);

        jobsPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Job dadosJobs = dataSnapshot.getValue(Job.class);
                //Log.i("Dados jobs", "nome:" + dadosJobs.getNome());
                Log.i("Dados jobs", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //montando a lista
        listJob = findViewById(R.id.listJob);

        //Cria adaptador para a lista
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                getApplicationContext(),
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1,
//                jobListaDefinitiva
//        );

//        listJob.setAdapter(adapter);
    }

    public void papularListaDeJob(){
        jobListaDefinitiva = (ListView) findViewById(R.id.listJob);
    }

}
