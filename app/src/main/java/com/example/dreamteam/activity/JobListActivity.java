package com.example.dreamteam.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dreamteam.R;
import com.example.dreamteam.model.Job;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobListActivity extends AppCompatActivity {

    private ListView listJob;
    private ListView jobListaDefinitiva;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseReference;

    //video 2
    private List<Job> jobs = new ArrayList<Job>();
    private ArrayAdapter<Job> arrayAdapterJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

//        DatabaseReference jobs = reference.child("jobs");

        //DatabaseReference jobsPesquisa = jobs.child("-LfFnv8vs6x2Fian5G2r");

        //Por meio de pesquisa direto na base
        //Query jobsPesquisa = jobs.orderByChild("nome").equalTo("teste job003");

        //limitar pesquisa ao numero determinado
        //Query jobsPesquisa = jobs.orderByKey().limitToFirst(2);

        //pesquisa com todos os jobs
//        Query jobsPesquisa = jobs.orderByChild("nome");


//        jobsPesquisa.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Job dadosJobs = dataSnapshot.getValue(Job.class);
//                //Log.i("Dados jobs", "nome:" + dadosJobs.getNome());
//                Log.i("Dados jobs", dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        //Cria adaptador para a lista
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                getApplicationContext(),
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1,
//                jobListaDefinitiva
//        );

//        listJob.setAdapter(adapter);

        inicializarFirebase();
        eventoDataBase();
        jobListaDefinitiva = (ListView) findViewById(R.id.listJob);
        listJob = findViewById(R.id.listJob);
    }

    private void eventoDataBase() {
       databaseReference.child("jobs").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               jobs.clear();
               for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                   Job job = objSnapshot.getValue(Job.class);
                   jobs.add(job);
               }
//               arrayAdapterJob = new ArrayAdapter<Job>(JobListActivity.this, R.layout.list_job_adapter,jobs);
               arrayAdapterJob = new ArrayAdapter<Job>(JobListActivity.this,android.R.layout.simple_list_item_1,jobs);
               jobListaDefinitiva.setAdapter(arrayAdapterJob);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }

    private  void inicializarFirebase(){
        FirebaseApp.initializeApp(JobListActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.setPersistenceEnabled(true);
        reference = firebaseDatabase.getReference();
    }

//    public void papularListaDeJob(){
//        jobListaDefinitiva = (ListView) findViewById(R.id.listJob);
//    }

}
