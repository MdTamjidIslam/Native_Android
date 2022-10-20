package com.example.todo.components;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.callbacks.OnItemClickListener;
import com.example.todo.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users = new ArrayList<>();
    private OnItemClickListener listener;

    public void attachListener(@NonNull OnItemClickListener listener){
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void submitList(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_drop_down, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvName.setText(user.getName());
        holder.tvUserName.setText(user.getUsername());
        holder.tvEmail.setText(user.getEmail());
        holder.tvUid.setText(String.valueOf(user.getId()));
        holder.itemView.setOnClickListener(view -> listener.onClick(user));
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvUserName, tvEmail, tvUid;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUid = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            tvEmail = itemView.findViewById(R.id.tv_user_mail);
        }
    }
}
