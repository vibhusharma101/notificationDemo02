package com.vibhusharma.notificationdemo02;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends Fragment {


    private List<Users> userList;
    private UsersRecyclerAdapter myAdapter;

    private RecyclerView mUsersListView;
private FirebaseFirestore mFirestore;





    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_users, container, false);


       mFirestore = FirebaseFirestore.getInstance();
       mUsersListView = view.findViewById(R.id.myRecyclerView);
       userList = new ArrayList<>();
       myAdapter = new UsersRecyclerAdapter(userList,getContext());
       mUsersListView.setHasFixedSize(true) ;
       mUsersListView.setLayoutManager(new LinearLayoutManager(container.getContext()));
       mUsersListView.setAdapter(myAdapter);


       return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        userList.clear();
        mFirestore.collection("Users").addSnapshotListener(getActivity(),new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                for(DocumentChange doc : documentSnapshots.getDocumentChanges())
                {
                 if(doc.getType()==DocumentChange.Type.ADDED)
                    {
                        String user_id = doc.getDocument().getId();
                       Users users = doc.getDocument().toObject(Users.class).withId(user_id);
                        userList.add(users);
                        myAdapter.notifyDataSetChanged();


                                    }


                }



            }
        });


    }
}
