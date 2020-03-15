package com.example.gitrepositoryapi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitrepositoryapi.connect.ProjectRepository
import com.example.gitrepositoryapi.connect.Resource
import com.example.gitrepositoryapi.model.User
import kotlinx.coroutines.launch

class DetailUserViewModel(private val user : String) : ViewModel() {
    private val mUser: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }
    private val mError: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun loadData() = run {
        viewModelScope.launch {
            when (val resp = ProjectRepository().getUser(user)) {
                is Resource.Success -> mUser.postValue(resp.data)
                is Resource.Error   -> mError.postValue("ERROR")
                is Resource.Loading ->  Log.d("LOADING","LOADING")

            }
        }
    }

    fun getUserDetail() = mUser
    fun getUserError () = mError
}
