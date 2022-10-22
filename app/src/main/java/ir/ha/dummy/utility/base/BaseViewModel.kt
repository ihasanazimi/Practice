package ir.ha.dummy.utility.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ir.soleymani.clinic.utility.extentions.toEvent

abstract class BaseViewModel : ViewModel() {

    open val composable = CompositeDisposable()
    open val errorLiveData = MutableLiveData<ArrayList<String>>()
    open var progressLiveData = MutableLiveData<Boolean>(false)

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



