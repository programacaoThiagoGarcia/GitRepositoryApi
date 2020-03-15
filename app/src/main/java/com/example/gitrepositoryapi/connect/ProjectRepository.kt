package com.example.gitrepositoryapi.connect


import com.example.gitrepositoryapi.model.Repository
import com.example.gitrepositoryapi.model.User
import java.lang.Exception


class ProjectRepository {
     suspend fun getRepository(): Resource<List<Repository>> {
         return try {
             val response =  Connect.getConnect().getRepository()
             if (response.isSuccessful){
                 Resource.Success(response.body())
             }else{
                 Resource.Error("ERRO")
             }
         }catch (e : Exception){
             Resource.Error(e.message.toString())
         }
     }

    suspend fun getUser(user : String) : Resource<User>{
        return try {
            val response = Connect.getConnect().getUser(user)
            if (response.isSuccessful){
                Resource.Success(response.body())
            }else{
                Resource.Error("ERRO")
            }
        }catch (e : Exception){
            Resource.Error(e.message.toString())
        }
    }
}

