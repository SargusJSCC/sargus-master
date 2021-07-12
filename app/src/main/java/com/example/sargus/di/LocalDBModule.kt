package com.example.sargus.di

import android.app.Application
import com.example.sargus.data.local.AppDatabase
import com.example.sargus.data.local.daos.PostDao
import com.example.sargus.data.local.daos.UserDao
import com.example.sargus.utility.AppPref
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDBModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    internal fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    internal fun provideUserPosts(appDatabase: AppDatabase): PostDao {
        return appDatabase.userPosts()
    }

    @Provides
    @Singleton
    internal fun provideSharedPref(application: Application): AppPref{
        return AppPref(application)
    }

}