package com.vibhusharma.notificationdemo02;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterationActivity extends AppCompatActivity {



    private CircleImageView myImageView;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passEditText;
    private Button registerBtn;
    private Button backBtn;
    private Uri imageUri;

    private FirebaseAuth mAuth;
    private StorageReference mStorage;

    private FirebaseFirestore mFirestore;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        myImageView = findViewById(R.id.profileImageView);
        nameEditText = findViewById(R.id.nameEditText1);
        emailEditText = findViewById(R.id.emailEditText1);
        passEditText = findViewById(R.id.passEditText1);
        registerBtn = findViewById(R.id.registerBtn1);
        backBtn = findViewById(R.id.backtoLoginPage);
        mStorage = FirebaseStorage.getInstance().getReference().child("images");
        mAuth = FirebaseAuth.getInstance();

        mFirestore = FirebaseFirestore.getInstance();


        myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),10);

            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imageUri!=null)
                {
                    final String name = nameEditText.getText().toString();
                    String email = emailEditText.getText().toString();
                    String password = passEditText.getText().toString();

                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if(task.isSuccessful())
                            {


                                 final String user_id= mAuth.getCurrentUser().getUid();
                                 final StorageReference user_profile = mStorage.child(user_id+".jpg");
                                 user_profile.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                     @Override
                                     public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> uploadTask) {
                                         if(uploadTask.isSuccessful())
                                         {



                                                     final String token_id = FirebaseInstanceId.getInstance().getToken();
                                                     user_profile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                         @Override
                                                         public void onSuccess(Uri uri) {

                                                             String download_url =uri.toString();
                                                             Map<String,Object> userMap = new HashMap<>();
                                                             userMap.put("name",name);
                                                             userMap.put("image",download_url);
                                                             userMap.put("token_id",token_id);




                                                             mFirestore.collection("Users").document(user_id).set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                 @Override
                                                                 public void onSuccess(Void aVoid) {

                                                                     sendToMain();
                                                                 }
                                                             });



                                                         }
                                                     });







                                         }

                                     }
                                 });

                            }

                            else
                            {

                                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
                            }




                        }
                    });


                }







            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }


    private void sendToMain()
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10)
        {
            imageUri = data.getData();
            myImageView.setImageURI(imageUri);




        }

    }
}
