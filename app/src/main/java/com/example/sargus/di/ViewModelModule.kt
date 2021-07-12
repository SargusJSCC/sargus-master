package com.example.sargus.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sargus.factory.AppViewModelFactory
import com.example.sargus.view.home.viewmodel.DetailsViewModel
import com.example.sargus.view.home.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel::class)
//    protected abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    protected abstract fun bindHomeViewModel(homeViewModel: HomeViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    protected abstract fun bindDetailsViewModel(detailsViewModel: DetailsViewModel):ViewModel

}