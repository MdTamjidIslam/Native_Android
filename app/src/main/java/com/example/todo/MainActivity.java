package com.example.todo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.components.CustomBottomSheet;
import com.example.todo.components.TodoAdapter;

public class MainActivity extends AppCompatActivity {

    private TodoAdapter mAdapter;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mAdapter = new TodoAdapter();

        mViewModel.loadUsers();

        mViewModel.getUsers().observe(this, users -> {
            CustomBottomSheet sheet = new CustomBottomSheet(this, users, user -> mViewModel.loadTodos(user.getId()));
            sheet.show();
        });

        mViewModel.getTodos().observe(this, todos -> mAdapter.submitList(todos));

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            mViewModel.loadUsers();
            return true;
        } else {
            return false;
        }
    }

}