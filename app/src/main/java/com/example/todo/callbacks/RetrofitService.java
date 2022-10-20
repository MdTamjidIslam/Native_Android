package com.example.todo.callbacks;

import com.example.todo.pojo.Todo;
import com.example.todo.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("todos")
    Call<List<Todo>> getTodos();

}
