package com.example.gitrepositoryapi.connect

sealed class Resource<out  T>{
    data class  Success<out T>(val data: T?): Resource<T>()
    data class Error<T>(val msg: String , val  data: T? = null): Resource<T>()
    data class Loading<T>(val data: T? = null): Resource<T>()

}
