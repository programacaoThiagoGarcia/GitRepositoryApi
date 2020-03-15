package com.example.gitrepositoryapi.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.setImageFromUrl(url : String?){
    url?.let {
        Picasso.get().load(it).into(this)
    }

}