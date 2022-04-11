package ir.ha.myapplication.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ir.ha.myapplication.BaseViewModel
import ir.ha.myapplication.model.Article
import ir.ha.myapplication.model.Banner
import ir.ha.myapplication.repo.BannerRepository
import ir.ha.myapplication.services.http.apiService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SampleViewModel(private val bannerRepository: BannerRepository) : BaseViewModel() {


    val errorLiveData = MutableLiveData<String>()
    val coroutinesExecutionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        errorLiveData.value = throwable.message
    }

     suspend fun getAllArticle() : LiveData<List<Article>>{
        val liveData  = MutableLiveData<List<Article>>()
        var res : ArrayList<Article> = arrayListOf()
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


    fun getBanners() : LiveData<List<Banner>>{
        val liveData = MutableLiveData<List<Banner>>()
        val banners = bannerRepository.getAllBanner()
        liveData.value = banners
        return liveData
    }

}