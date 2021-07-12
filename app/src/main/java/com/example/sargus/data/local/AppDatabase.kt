package com.example.sargus.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sargus.data.local.daos.PostDao
import com.example.sargus.data.local.daos.UserDao
import com.example.sargus.data.local.model.UserRepos
import com.example.sargus.data.local.model.Users

@Database(entities = [Users::class, UserRepos::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

      abstract fun userDao(): UserDao
      abstract fun userPosts(): PostDao

      companion object {

            @Volatile
            private var INSTANCE: AppDatabase? = null

            fun getInstance(context: Application): AppDatabase = INSTANCE ?: synchronized(this) {
                  INSTANCE ?: createDatabase(context).also { INSTANCE = it }
            }

            private fun createDatabase(context: Application) =
                  Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
      }
}

const val DB_NAME="sargus.db"