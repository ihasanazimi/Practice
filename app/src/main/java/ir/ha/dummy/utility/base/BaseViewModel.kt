package ir.ha.dummy.utility.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    open val composable = CompositeDisposable()
        get() = field


    override fun onCleared() {
        composable.clear()
        super.onCleared()
    }
}