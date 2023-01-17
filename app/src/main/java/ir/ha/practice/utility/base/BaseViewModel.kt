package ir.ha.practice.utility.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    open val composable = CompositeDisposable()
    open val errorLiveData = MutableLiveData<ArrayList<String>>()
    open var showProgress = MutableLiveData<Boolean>(false)

    // for clear data after change state
    open fun clearErrorLiveData(){
        val data = errorLiveData.value?.apply { clear() } ?: arrayListOf()
        errorLiveData.value = data
    }

    override fun onCleared() {
        composable.clear()
        clearErrorLiveData()
        super.onCleared()
    }
}



