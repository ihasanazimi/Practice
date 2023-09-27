package ir.ha.practice.utility.remote.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.ha.practice.App

class MyFactory(app : App) : ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }

}