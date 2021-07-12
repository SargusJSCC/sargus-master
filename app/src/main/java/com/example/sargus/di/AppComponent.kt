package com.example.sargus.di

import android.app.Application
import com.example.sargus.utility.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,AppModule::class,NetworkModule::class,ActivityBuilder::class])
interface AppComponent : AndroidInjector<MyApp> {
    override fun inject(instance: MyApp?)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }



}