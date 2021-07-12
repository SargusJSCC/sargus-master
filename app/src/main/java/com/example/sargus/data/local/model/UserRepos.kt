package com.example.sargus.data.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts", indices = [Index(value = ["id"], unique = true)])
data class UserRepos(

    @field:SerializedName("userId")
    val userId: Int,

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("body")
    val body: String?
){
    fun getUserReposUserId(): String ="UserID :=$userId"
    fun getUserReposId(): String ="$id"
    fun getUserReposTitle():String="$title"
    fun getUserReposBody():String="$body"
}



