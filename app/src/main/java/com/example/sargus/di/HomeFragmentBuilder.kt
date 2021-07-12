package com.example.sargus.di

import com.example.sargus.view.home.fragment.DetailsFragment
import com.example.sargus.view.home.fragment.HomeFragment
import com.example.sargus.view.login.LoginFragment
import com.example.sargus.view.login.RegisterFragment
import com.example.sargus.view.settings.SettingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindLoginFragment() : LoginFragment

    @ContributesAndroidInjector
    abstract fun bindRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindSettingFragment(): SettingFragment

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment


}