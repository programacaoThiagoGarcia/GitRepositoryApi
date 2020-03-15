package com.example.gitrepositoryapi.ui.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.gitrepositoryapi.R
import com.example.gitrepositoryapi.databinding.DetailRepositoryFragmentBinding
import com.example.gitrepositoryapi.model.User
import com.example.gitrepositoryapi.ui.viewmodel.DetailUserViewModel
import com.example.gitrepositoryapi.ui.viewmodel.DetailUserViewModelFactory

class DetailUser : Fragment() {
    private lateinit var viewModelFactory : DetailUserViewModelFactory
    private  lateinit var binding : DetailRepositoryFragmentBinding

    companion object {
        fun newInstance() = DetailUser()
    }

    private lateinit var viewModel: DetailUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.detail_repository_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareViewModel()
    }

    private fun prepareViewModel() {
        viewModelFactory = DetailUserViewModelFactory("programacaoThiagoGarcia")
        viewModel = ViewModelProvider(this,viewModelFactory).get(DetailUserViewModel::class.java)
        viewModel.getUserDetail().observe(viewLifecycleOwner, Observer {
            it?.let {
                prepareView(it)
            }
        })
        viewModel.getUserError().observe(viewLifecycleOwner, Observer {
            Log.d(this::class.java.toString().toUpperCase(),it.toUpperCase())
        })
    }

    private fun prepareView(user: User) {

    }

}
