package ir.ha.practice.utility.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    open val compositeDisposable = CompositeDisposable() // for RxJava
    open val errorLiveData = MutableLiveData<ArrayList<String>>()
    open var showProgress = MutableLiveData<Boolean>(false)

    // clear data after change state and was destroyed view
    open fun clearErrorLiveData(){
        val data = errorLiveData.value?.apply { clear() } ?: arrayListOf()
        errorLiveData.value = data
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear() // for RxJava
        clearErrorLiveData()
    }
}



