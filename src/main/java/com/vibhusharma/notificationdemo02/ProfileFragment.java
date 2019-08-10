package com.vibhusharma.notificationdemo02;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class ProfileFragment extends Fragment {


    private FirebaseAuth mAuth;
    private Button logoutBtn;

    private FirebaseFirestore mFirestore;
    private  String user_id;

    private TextView nameTextView;
    private ImageView myImageView;



    public ProfileFragment() {




    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        nameTextView = view.findViewById(R.id.nameTextView);
        myImageView = view.findViewById(R.id.myImageView);
        user_id= FirebaseAuth.getInstance().getCurrentUser().getUid();

        mFirestore.collection("Users").document(user_id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String user_name = documentSnapshot.getString("name");
                String user_image = documentSnapshot.getString("image");
                nameTextView.setText(user_name);
                RequestOptions placeHolderOptions = new RequestOptions();
                placeHolderOptions.placeholder(R.mipmap.ic_launcher);

                Log.i("user Url ",user_image);
                Glide.with(container.getContext()).setDefaultRequestOptions(placeHolderOptions).load(user_image).into(myImageView);




            }
        });


        logoutBtn = (Button)view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Map<String,Object> tokenMapRemove = new HashMap<>();
                tokenMapRemove.put("token_id", FieldValue.delete());
                mFirestore.collection("Users").document(user_id).update(tokenMapRemove).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mAuth.signOut();
                        Intent intent = new Intent(container.getContext(),LoginActivity.class);

                        startActivity(intent);
                        getActivity().finish();

                    }
                });



            }
        });



        return view;
    }

}
