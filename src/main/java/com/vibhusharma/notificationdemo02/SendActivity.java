package com.vibhusharma.notificationdemo02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SendActivity extends AppCompatActivity {


    private TextView sampTextView;
    private String userId;
    private EditText messageEditText;
    private Button sendBtn;
    private FirebaseFirestore mFireStore;
    private String current_UserId;
private String user_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        sampTextView = findViewById(R.id.userIdTextView);
        messageEditText = findViewById(R.id.messageEditText);
        sendBtn = findViewById(R.id.sendBtn);
        mFireStore = FirebaseFirestore.getInstance();
        current_UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userId = getIntent().getStringExtra("user_Id");
        user_name = getIntent().getStringExtra("user_Name");

        sampTextView.setText(user_name);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = messageEditText.getText().toString();

                if(!message.equals(""))
                {
                    Map<String,Object>notificationMessage = new HashMap<>();
                    notificationMessage.put("message",message);
                    notificationMessage.put("from",current_UserId);
                    mFireStore.collection("Users").document(userId).collection("Notifications").add(notificationMessage).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(getApplicationContext(),"Success Sent",Toast.LENGTH_SHORT).show();
                            messageEditText.setText("");
                        }
                    });




                }






            }
        });





    }
}
