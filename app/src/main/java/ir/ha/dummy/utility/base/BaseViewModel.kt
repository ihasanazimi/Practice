package ir.ha.dummy.utility.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    open val composable = CompositeDisposable()
        get() = field

    open val errorLiveData = MutableLiveData<ArrayList<String>>()
        get() = field

    open var progressLiveData = MutableLiveData<Boolean>(false)
        get() = field
        set(value) { field = value }

    // for clear data after change state
    open fun clearErrorLiveData(){
        errorLiveData.value?.clear()
    }





    override fun onCleared() {
        composable.clear()
        clearErrorLiveData()
        super.onCleared()
    }
}