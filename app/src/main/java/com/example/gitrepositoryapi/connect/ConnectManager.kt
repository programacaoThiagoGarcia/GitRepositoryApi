package com.example.gitrepositoryapi.connect

import com.example.gitrepositoryapi.model.Repository
import com.example.gitrepositoryapi.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ConnectManager {
    @GET("/users")
    suspend fun getRepository() : Response<List<Repository>>

    @GET("/users/{user}")
    suspend fun getUser(@Path("user")user : String) : Response<User>
}

