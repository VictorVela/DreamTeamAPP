package com.example.dreamteam.fragment;



import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dreamteam.MainActivity;
import com.example.dreamteam.R;
import com.example.dreamteam.model.Job;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobFragment extends Fragment implements View.OnClickListener {

    Button myButton;

    Job job = new Job();

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    private EditText nomeJobIn ,valorJobIn;

    private ImageView imagemFoto;

    public JobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle("post a new job");

        View myView = inflater.inflate(R.layout.fragment_job, container, false);

        myButton = (Button) myView.findViewById(R.id.id_salvarJob);

        nomeJobIn = myView.findViewById(R.id.id_nome_job);

        valorJobIn = myView.findViewById(R.id.id_job_valor);

        myButton.setOnClickListener(this);

        imagemFoto = myView.findViewById(R.id.iamagemWeb);

//        return inflater.inflate(R.layout.fragment_job, container, false);
        return myView;


    }

    @Override
    public void onClick(View v) {
        // implements your things

        //Configurar para imagem ser salva em memoria
        imagemFoto.setDrawingCacheEnabled(true);
        imagemFoto.buildDrawingCache();

        //Recupera bitmap da imagem (imagem a ser carregada)
        Bitmap bitmap = imagemFoto.getDrawingCache();

        //Comprimir bitmap para um formato png/jpg
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75, baos);

        //converte o baos para pixel brutos em uma matriz de bytes
        // (dados da imagem)
        byte[] dadosImagem = baos.toByteArray();

        //Definir nós para o storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imagens = storageReference.child("imagens");

        //Nome iamagem
        String nomeArquivo = UUID.randomUUID().toString();
        StorageReference imagemRef = imagens.child(nomeArquivo +".jpg");

        //Retoran objeto que irá controlar o uploud
        UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

        uploadTask.addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Falha ao subir a imagem" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri url = taskSnapshot.getUploadSessionUri();
                Toast.makeText(getActivity(),"Imagem salva com sucesso" + url.toString() , Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(getActivity(),"Job salvo", Toast.LENGTH_SHORT).show();
        String nomeJob = nomeJobIn.getText().toString();
        String valorJob = valorJobIn.getText().toString();
        job.setNome(nomeJob);
        job.setValor(valorJob);
        DatabaseReference jobs = referencia.child("jobs");
        jobs.push().setValue(job);
        job.setNome(null);
        job.setValor(null);
        nomeJobIn.setText("");
        valorJobIn.setText("");

        nomeJobIn.requestFocus();
    }

}
