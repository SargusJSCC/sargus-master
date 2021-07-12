package com.example.sargus.view.home.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sargus.R
import com.example.sargus.data.local.model.Users
import com.example.sargus.databinding.UserListItemBinding
import com.example.sargus.utility.Logger.setLog


class RvAdapterUsers() : RecyclerView.Adapter<RvAdapterUsers.UsersVH>() {

    private var dataList = mutableListOf<Users>()
    private var login= MutableLiveData<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVH {

        val view = DataBindingUtil
            .inflate<UserListItemBinding>(LayoutInflater.from(parent.context),
                    R.layout.user_list_item,
                    parent,
                    false)

        return UsersVH(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: UsersVH, position: Int) {
      holder.apply {
           onBindData(position)

          binding.buttonDetails.setOnClickListener{

              val bundle = Bundle()

              bundle.putString("param1", (position + 1).toString())
              bundle.putString("param2", dataList[position].getUserName())
              bundle.putString("param3", dataList[position].getUserPhone())
              bundle.putString("param4", dataList[position].getUserEmail())

              it.findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)

          }
        }
    }

    inner class UsersVH(val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBindData(
            position: Int
        ) {
            setLog(message = dataList[position].toString())
            binding.item=dataList[position]
            binding.executePendingBindings()
        }
    }


    fun addDataList(list: List<Users>) {
        dataList.clear()
        dataList.addAll(list)
        setLog(message = "${dataList.size} after add new ")
        notifyDataSetChanged()
    }


    fun getSelectedLogin(): LiveData<String> {
        return login
    }

}