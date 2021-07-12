package com.example.sargus.utility

import android.util.Log
import com.example.sargus.BuildConfig

object Logger {

    fun setLog(tag:String="Logs",message:String){
        if (BuildConfig.DEBUG){
            Log.e(tag,message)
        }
    }
}