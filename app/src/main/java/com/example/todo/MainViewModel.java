package com.example.todo;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todo.api.RetrofitClient;
import com.example.todo.callbacks.RetrofitService;
import com.example.todo.pojo.Todo;
import com.example.todo.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<User>> mUsersLiveData;
    private final MutableLiveData<List<Todo>> mTodosLiveData;
    private final RetrofitService mDao;

    public MainViewModel() {
        this.mUsersLiveData = new MutableLiveData<>();
        this.mTodosLiveData = new MutableLiveData<>();
        this.mDao = new RetrofitClient().getApi(RetrofitService.class);
    }

    public void loadUsers(){
        mDao.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                mUsersLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {

            }
        });
    }

    public void loadTodos(int id){
        mDao.getTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Todo>> call, @NonNull Response<List<Todo>> response) {
                mTodosLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Todo>> call, @NonNull Throwable t) {

            }
        });
    }

    public LiveData<List<User>> getUsers() {
        return mUsersLiveData;
    }

    public LiveData<List<Todo>> getTodos() {
        return mTodosLiveData;
    }
}
