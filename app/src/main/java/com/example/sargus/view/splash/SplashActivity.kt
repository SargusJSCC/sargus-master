package com.example.sargus.view.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.sargus.R
import com.example.sargus.utility.start
import com.example.sargus.view.home.HomeActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SplashActivity : AppCompatActivity(),HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            start(HomeActivity::class.java)
            finish()
        }, 3000)
    }

    override fun androidInjector() =dispatchingAndroidInjector
}