package com.example.sargus.view.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sargus.R
import com.example.sargus.data.local.model.UserRepos
import com.example.sargus.databinding.PostListItemBinding
import com.example.sargus.utility.Logger.setLog


class RvAdapterPosts() : RecyclerView.Adapter<RvAdapterPosts.PostsVH>() {

    private var dataList = mutableListOf<UserRepos>()
    private var login= MutableLiveData<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsVH {

        val view = DataBindingUtil
            .inflate<PostListItemBinding>(LayoutInflater.from(parent.context),
                    R.layout.post_list_item,
                    parent,
                    false)

        return PostsVH(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: PostsVH, position: Int) {
      holder.apply {
           onBindData(position)
        }
    }

    inner class PostsVH(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBindData(
            position: Int
        ) {
            setLog(message = dataList[position].toString())
            binding.item=dataList[position]
            binding.executePendingBindings()

        }
    }


    fun addDataList(list: List<UserRepos>) {
        dataList.clear()
        dataList.addAll(list)
        setLog(message = "${dataList.size} after add new ")
        notifyDataSetChanged()
    }


    fun getSelectedLogin(): LiveData<String> {
        return login
    }

}