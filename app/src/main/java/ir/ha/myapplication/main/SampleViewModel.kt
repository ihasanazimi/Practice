package ir.ha.myapplication.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.ha.myapplication.BaseViewModel
import ir.ha.myapplication.model.ArticleModel
import ir.ha.myapplication.services.apiService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SampleViewModel : BaseViewModel() {


    val errorLiveData = MutableLiveData<String>()
    val coroutinesExecutionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        errorLiveData.value = throwable.message
    }

     suspend fun getAllArticle() : LiveData<List<ArticleModel>>{
        val liveData  = MutableLiveData<List<ArticleModel>>()
        var res : ArrayList<ArticleModel> = arrayListOf()
        viewModelScope.launch(coroutinesExecutionHandler) {
            val articles = apiService.getAllArticle()
            for (i in articles){
                res.add(i)
            }


        }
         delay(2000)
         liveData.value = res
        return liveData
    }

}