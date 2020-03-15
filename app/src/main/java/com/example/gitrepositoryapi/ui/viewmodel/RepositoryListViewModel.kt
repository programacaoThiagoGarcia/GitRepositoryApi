package com.example.gitrepositoryapi.ui.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitrepositoryapi.connect.ProjectRepository
import com.example.gitrepositoryapi.connect.Resource
import com.example.gitrepositoryapi.model.Repository
import kotlinx.coroutines.launch


class RepositoryListViewModel : ViewModel() {
    private val mRepositoryList: MutableLiveData<List<Repository>> by lazy {
        MutableLiveData<List<Repository>>()
    }

    private val mError: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun loadData() = run {
        viewModelScope.launch {
            when(val resp = ProjectRepository().getRepository()){
                is Resource.Success -> mRepositoryList.postValue(resp.data)
                is Resource.Error -> mError.postValue("ERROR")
                is Resource.Loading -> Log.d("LOADING","LOADING")
            }
        }
    }

    fun getRepositoryList() = mRepositoryList
    fun getRepositoryError() = mError
}
