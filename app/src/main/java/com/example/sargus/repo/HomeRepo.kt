package com.example.sargus.repo

import com.example.sargus.data.local.AppDatabase
import com.example.sargus.data.local.daos.UserDao
import com.example.sargus.data.local.model.Users
import com.example.sargus.data.network.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepo @Inject constructor(
    private val db: AppDatabase,
    private val dao: UserDao,
    private val apiInterface: ApiInterface
) {

    @Throws(Exception::class)
    suspend fun getUsers(): List<Users> = withContext(Dispatchers.IO) {
        val list = mutableListOf<Users>()
            list += apiInterface.getUser()
            dao.insertUsers(list)

        list
    }
    
}