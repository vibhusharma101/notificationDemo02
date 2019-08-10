package com.vibhusharma.notificationdemo02;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder> {

    private List<Users> usersList;
    private Context context;


    public UsersRecyclerAdapter(List<Users>usersList,Context context)
    {

        this.usersList=usersList;
        this.context = context;


    }




    public class ViewHolder extends RecyclerView.ViewHolder
    {

        private View mView;
        private TextView uNameTextView;
        private CircleImageView myImageView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            uNameTextView = mView.findViewById(R.id.uNametextView);
            myImageView = mView.findViewById(R.id.listImageView);



        }





    }







    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_list_item,viewGroup,false);

        return  new ViewHolder(view);





    }

    @Override
    public void onBindViewHolder(@NonNull UsersRecyclerAdapter.ViewHolder viewHolder, int i) {


final String user_name  = usersList.get(i).getName();
        viewHolder.uNameTextView.setText(user_name);

        CircleImageView circleImageView = viewHolder.myImageView;

        Glide.with(context).load(usersList.get(i).getImage()).into(circleImageView);

        final String user_Id  = usersList.get(i).userId;

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent(context,SendActivity.class);
                sendIntent.putExtra("user_Id",user_Id);
                sendIntent.putExtra("user_Name",user_name);
                context.startActivity(sendIntent);



            }
        });






    }

    @Override
    public int getItemCount() {
        return usersList.size() ;
    }
}
