package com.android.nextos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.nextos.databinding.UserItemCardBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.holder> {
    ArrayList<UserModel> userModelArrayList;
    Context context;

    public UserAdapter(ArrayList<UserModel> userModelArrayList, Context context) {
        this.userModelArrayList = userModelArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public UserAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item_card, parent , false);
        return new holder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.holder holder, int position) {
        UserModel userModel = userModelArrayList.get(position);
        Picasso.get()
                .load(userModel.getPhoto())
                .into(holder.binding.photo);
        holder.binding.name.setText(userModel.getName());
        holder.binding.summery.setText(userModel.getSummery());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = userModel.getLink();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                view.getContext().startActivity(browserIntent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        UserItemCardBinding binding;
        public holder(@NonNull View itemView) {
            super(itemView);
            binding = UserItemCardBinding.bind(itemView);
        }
    }
}
