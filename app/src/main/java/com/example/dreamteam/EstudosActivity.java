package com.example.dreamteam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EstudosActivity extends AppCompatActivity {

    /*
    este método pega a referencia inicial do databese do firebase no caso "dream", porem
    é possivel passar onde ele deve começar, basta passar no getReference("nome")
     */
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudos);

        /*
        neste método estamos criando um novo filho para o primeiro caminho do firebase, nó padrão
         */
        referencia.child("job").setValue("100");
    }
}
