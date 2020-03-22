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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.gitrepositoryapi.R
import com.example.gitrepositoryapi.databinding.UserListFragmentBinding
import com.example.gitrepositoryapi.model.Repository
import com.example.gitrepositoryapi.ui.adapter.ItemListClickCallback
import com.example.gitrepositoryapi.ui.adapter.UserListAdapter
import com.example.gitrepositoryapi.ui.viewmodel.RepositoryListViewModel

class RepositoryList : Fragment(), ItemListClickCallback {
    private lateinit var mBinding: UserListFragmentBinding
    private lateinit var viewModel: RepositoryListViewModel
    private var mRepositoryListAdapter: UserListAdapter? = null

    companion object {
        fun newInstance() = RepositoryList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.user_list_fragment, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareview()
        prepareViewModel()
    }

    private fun prepareview() {
        mRepositoryListAdapter = context?.let {
            UserListAdapter(it)
        }
        mRepositoryListAdapter?.let {
            it.connectToFragment(this)
        }
        mBinding.rvUser.apply {
            setHasFixedSize(true)
            adapter = mRepositoryListAdapter
            layoutManager = activity?.let {
                LinearLayoutManager(it)
            }
        }
    }

    private fun prepareViewModel() {
        viewModel = ViewModelProvider(this).get(RepositoryListViewModel::class.java)
        viewModel.getRepositoryList().observe(viewLifecycleOwner, Observer {
            it?.let {
                mRepositoryListAdapter?.setUserList(it)
            }
        })
        viewModel.getRepositoryError().observe(viewLifecycleOwner, Observer {
            Log.d(RepositoryList.javaClass.name.toUpperCase(), it)
        })
        viewModel.loadData()
    }

    override fun <T>itemListClickCallbackReturn(item: T) {
        view?.let {
            val repository = item as Repository
//            it.findNavController().navigate(R.id.action_userList_to_detailRepository)
            it.findNavController().navigate(RepositoryListDirections.actionUserListToDetailRepository(repository.login))
        }
    }

}
