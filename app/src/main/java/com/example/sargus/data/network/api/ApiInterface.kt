package com.example.sargus.data.network.api

import com.example.sargus.data.local.model.UserRepos
import com.example.sargus.data.local.model.Users
import com.example.sargus.rest.Endpoints
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(Endpoints.GET_USERS)
    suspend fun getUser(): List<Users>

    @GET(Endpoints.GET_POSTS)
    suspend fun getAllPosts(): List<UserRepos>

    @GET(Endpoints.GET_POST_USER)
    suspend fun getPosts(@Query("userId") id: String?): List<UserRepos>

}