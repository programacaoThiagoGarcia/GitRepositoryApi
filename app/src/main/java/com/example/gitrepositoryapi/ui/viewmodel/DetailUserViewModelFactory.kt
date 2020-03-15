package com.example.gitrepositoryapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class DetailUserViewModelFactory(private val user : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = DetailUserViewModel(user)
        viewModel.loadData()
        return  viewModel as T
    }
}