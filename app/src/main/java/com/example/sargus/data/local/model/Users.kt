package com.example.sargus.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class Users(

    @field:SerializedName("id")
    val id: Int,
    @PrimaryKey
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("phone")
    val phone: String,
    @field:SerializedName("email")
    val email: String?
){
    fun getUserIdName():String="ID :=$id, Name :=$name"
    fun getUserName():String ="$name"
    fun getUserPhone():String="$phone"
    fun getUserEmail():String="$email"
}



