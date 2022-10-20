package com.example.todo.components;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.callbacks.OnItemClickListener;
import com.example.todo.R;
import com.example.todo.pojo.User;

import java.util.List;

public class CustomBottomSheet extends Dialog {

    private final List<User> mUsers;
    private final OnItemClickListener mListener;

    public CustomBottomSheet(@NonNull Context context, @NonNull List<User> users, @NonNull OnItemClickListener mListener) {
        super(context);
        this.mUsers = users;
        this.mListener = mListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_drop_down_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ImageButton button = findViewById(R.id.close);
        UserAdapter adapter = new UserAdapter();
        adapter.attachListener(user -> {
            mListener.onClick(user);
            dismiss();
        });

        recyclerView.setAdapter(adapter);
        adapter.submitList(mUsers);

        button.setOnClickListener(view -> dismiss());

    }

}
