package com.example.sargus.view.home.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.example.sargus.BR
import com.example.sargus.baseclasses.BaseViewModel
import com.example.sargus.repo.HomeRepo
import com.example.sargus.repo.PostsRepo
import com.example.sargus.utility.Logger
import com.example.sargus.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val postsRepo: PostsRepo) :BaseViewModel() {

    var job: Job? = null

    @get:Bindable
    public var isLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)

        }


    fun getPosts(id: String): MutableLiveData<Resource<Any>> {
        job = Job()
        var result: MutableLiveData<Resource<Any>> = MutableLiveData()
        result.setValue(Resource.loading(null))
        job = GlobalScope.launch(Dispatchers.Main + job!!) {
            try {
                val list = postsRepo.getPost(id)
                Logger.setLog(message = list.toString())
                result.value = Resource.success(list)
            }catch (e:Exception){
                Logger.setLog(message = e.toString())
                result.value=Resource.error(e.localizedMessage,0)
            }
        }
        return result
    }

}