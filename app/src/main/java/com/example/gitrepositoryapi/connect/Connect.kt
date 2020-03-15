package com.example.gitrepositoryapi.connect

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Connect {
    private val retrofit  : Retrofit by lazy(
        Retrofit.Builder().baseUrl("https://api.github.com").addConverterFactory(
            GsonConverterFactory.create()
        )::build
    )
    fun getConnect() = retrofit.create(ConnectManager::class.java)
}