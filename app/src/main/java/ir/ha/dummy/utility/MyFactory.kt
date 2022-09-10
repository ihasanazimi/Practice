package ir.ha.dummy.utility

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.ha.dummy.App

class MyFactory(app : App) : ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }

}