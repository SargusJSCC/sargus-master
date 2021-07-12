package com.example.sargus.di

import com.example.sargus.utility.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class,LocalDBModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(myApp: MyApp)=myApp

}