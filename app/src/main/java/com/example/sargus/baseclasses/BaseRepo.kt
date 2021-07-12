package com.example.sargus.baseclasses

import com.example.sargus.data.network.api.ApiInterface
import com.example.sargus.utility.AppPref

open class BaseRepo(open val apiInterface:ApiInterface,open  val  pref:AppPref)