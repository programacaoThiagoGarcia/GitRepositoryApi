package com.example.gitrepositoryapi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepositoryapi.R
import com.example.gitrepositoryapi.databinding.UserListItemBinding
import com.example.gitrepositoryapi.model.Repository

class UserListAdapter(private val context : Context) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>(), ItemListClickCallback {
    private var mRepositoryList : List<Repository>? = null
    private var itemListClickCallback : ItemListClickCallback? = null

    class UserListViewHolder(private val item : UserListItemBinding, private val callback: ItemListClickCallback) : RecyclerView.ViewHolder(
        item.root
    ){
        fun bind(repository : Repository){
            item.user = repository
            item.callback = callback
        }

    }

    fun setUserList(repositoryList : List<Repository>){
        mRepositoryList = repositoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding : UserListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.user_list_item,parent,false)
        return UserListViewHolder(binding, this)
    }

    override fun getItemCount() = mRepositoryList?.size ?: 0

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val mUser = mRepositoryList?.get(position)
        mUser?.let {
            holder.bind(it)
        }
    }

    fun connectToFragment(fragment : ItemListClickCallback){
        itemListClickCallback = fragment
    }

    override fun <T>itemListClickCallbackReturn(item: T){
        itemListClickCallback?.itemListClickCallbackReturn(item as T)
    }

}