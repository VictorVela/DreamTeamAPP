package com.example.dreamteam;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText     emailImput,senhaImput;
    private FirebaseAuth firebaseAuth;
    String  email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailImput = findViewById(R.id.id_email);
        senhaImput = findViewById(R.id.id_senha);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    //-----------------VERIFICAR SE ESTÁ LOGADO---------------------------------------------------//
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null) {
            Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), InicioActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Você não está logado", Toast.LENGTH_SHORT).show();

        }
    }
    //-----------------VERIFICAR SE ESTÁ LOGADO---------------------------------------------------//

    //----------------------------------LOGAR------------------------------------------------------//
    public void singnIn(View view){
        final String login = emailImput.getText().toString();
        String senha = senhaImput.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(login,senha).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this,"Logado com sucesso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Inicio2Activity.class);
                //passar dados
                intent.putExtra("login do usuario",login);
                startActivity(intent);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"Erro ao logar", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    //----------------------------------LOGAR------------------------------------------------------//

    //----------------------------------NOVO USUARIO-----------------------------------------------//
    public void cadastrar(View view){
        Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(intent);
    }
    //----------------------------------NOVO USUARIO-----------------------------------------------//
}

