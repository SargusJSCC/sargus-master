package com.example.sargus.repo

import com.example.sargus.data.local.AppDatabase
import com.example.sargus.data.local.daos.PostDao
import com.example.sargus.data.local.daos.UserDao
import com.example.sargus.data.local.model.UserRepos
import com.example.sargus.data.local.model.Users
import com.example.sargus.data.network.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepo @Inject constructor(
    private val db: AppDatabase,
    private val dao: PostDao,
    private val apiInterface: ApiInterface
) {


    @Throws(Exception::class)
    suspend fun getAllPosts(): List<UserRepos> = withContext(Dispatchers.IO) {
        val list = mutableListOf<UserRepos>()
            list += apiInterface.getAllPosts()
            dao.insertPosts(list)
        list
    }


    @Throws(Exception::class)
    suspend fun getPost(id: String): List<UserRepos> = withContext(Dispatchers.IO) {
        val list = mutableListOf<UserRepos>()
        list += apiInterface.getPosts(id)
        dao.insertPosts(list)
        list
    }
    
}