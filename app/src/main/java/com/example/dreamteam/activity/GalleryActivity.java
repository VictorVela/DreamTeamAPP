package com.example.dreamteam.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamteam.R;
import com.example.dreamteam.model.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class GalleryActivity extends AppCompatActivity {

    private static  final int PICK_IMAGE_REQUEST = 1;

    private Button      mButtonChooseimage;
    private Button      mButtonUploud;
    private TextView    mTextViewShowuplouds;
    private EditText    mEditTextfileName;
    private ImageView   mImageView;
    private ProgressBar mProgressBar;

    private Uri mImageUri;

    private StorageReference  mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mButtonChooseimage   = findViewById(R.id.button_choose_image);
        mButtonUploud        = findViewById(R.id.button_upload);
        mTextViewShowuplouds = findViewById(R.id.text_view_show_uploads);
        mEditTextfileName    = findViewById(R.id.edit_text_file_name);
        mImageView           = findViewById(R.id.image_view);
        mProgressBar         = findViewById(R.id.progress_bar);

        mStorageRef          = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef         = FirebaseDatabase.getInstance().getReference("uploads");

        mButtonChooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUploud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();

            }
        });

        mTextViewShowuplouds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openImagesActivity();
            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
        Toast.makeText(GalleryActivity.this, "Galery", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST  && resultCode == RESULT_OK && data !=  null && data.getData() != null){
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(mImageView);
            Toast.makeText(GalleryActivity.this, "File selected", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if (mImageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(0);
                        }
                    }, 5000);

                    Toast.makeText(GalleryActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
//                    Upload uploud = new Upload(mEditTextfileName.getText().toString().trim(),taskSnapshot.getStorage().getDownloadUrl().toString());

//                    String uploadId = mDatabaseRef.push().getKey();
//                    mDatabaseRef.child(uploadId).setValue(uploud);


                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful());
                    Uri downloadUrl = urlTask.getResult();

//                    Log.d(TAG, "onSuccess: firebase download url: " + downloadUrl.toString());
                    Upload upload = new Upload(mEditTextfileName.getText().toString().trim(),downloadUrl.toString());

                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                   Toast.makeText(GalleryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    mProgressBar.setProgress((int)progress);
                }
            });
        }else {
            Toast.makeText(this,"No file selected",Toast.LENGTH_SHORT).show();
        }



    }

    private void openImagesActivity(){
        Intent intent = new Intent(this, ImagesActivity.class);
        startActivity(intent);
    }
}
