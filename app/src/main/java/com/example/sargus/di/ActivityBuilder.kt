package com.example.sargus.di

import com.example.sargus.view.home.HomeActivity
import com.example.sargus.view.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashScreen() : SplashActivity

    @ContributesAndroidInjector(modules = [HomeFragmentBuilder::class])
    abstract fun bindHomeScreen() :HomeActivity


}