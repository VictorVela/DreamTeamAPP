package com.example.dreamteam.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.dreamteam.R;
import com.example.dreamteam.model.Pessoa;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PessoaActivity extends AppCompatActivity {

    EditText edtNome, edtEmail;
    ListView listV_dados;

    //Conexão com o banco  FireBase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //lista
    private List<Pessoa> listPessoa  = new ArrayList<Pessoa>();
    private ArrayAdapter<Pessoa>  arrayAdapterPessoa;

    //update delete
    Pessoa pessoaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
        edtEmail = (EditText) findViewById(R.id.editEmail);
        edtNome = (EditText) findViewById(R.id.editNome);
        listV_dados =  (ListView)findViewById(R.id.listV_dados);

        inicializarFirebase();
        eventoDatabase();

        listV_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pessoaSelecionada = (Pessoa)parent.getItemAtPosition(position);
                edtNome.setText(pessoaSelecionada.getNome());
                edtEmail.setText(pessoaSelecionada.getEmail());
            }
        });
    }

    private void eventoDatabase() {
        databaseReference.child("Pessoa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPessoa.clear();
                for (DataSnapshot objSnapshop:dataSnapshot.getChildren()){
                    Pessoa p = objSnapshop.getValue(Pessoa.class);
                    listPessoa.add(p);
                }
                arrayAdapterPessoa = new ArrayAdapter<Pessoa>(PessoaActivity.this,android.R.layout.simple_list_item_1,listPessoa);
                listV_dados.setAdapter(arrayAdapterPessoa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(PessoaActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference =  firebaseDatabase.getReference();
    }

    //faltou esta parte no job

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int  id = item.getItemId();
        if(id == R.id.menu_novo){
            Pessoa p = new Pessoa();
            p.setUid(UUID.randomUUID().toString());
            p.setNome(edtNome.getText().toString());
            p.setEmail(edtEmail.getText().toString());
            databaseReference.child("Pessoa").child(p.getUid()).setValue(p);
            
            limaparCampos();
        }else if(id == R.id.menu_atualizar){
            Pessoa p  = new Pessoa();
            p.setUid(pessoaSelecionada.getUid());
            p.setNome(edtNome.getText().toString().trim());
            p.setEmail(edtEmail.getText().toString().trim());
            databaseReference.child("Pessoa").child(p.getUid()).setValue(p);
            limaparCampos();
        }else if(id == R.id.menu_deletar){
            Pessoa  p = new Pessoa();
            p.setUid(pessoaSelecionada.getUid());
            databaseReference.child("Pessoa").child(p.getUid()).removeValue();
            limaparCampos();
        }
        return true;
    }

    private void limaparCampos() {
        edtEmail.setText("");
        edtNome.setText("");
    }
}