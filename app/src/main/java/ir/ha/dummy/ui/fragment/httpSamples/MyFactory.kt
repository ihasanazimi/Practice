package ir.ha.dummy.ui.fragment.httpSamples

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.ha.dummy.ApplicationLoader

class MyFactory(applicationLoader : ApplicationLoader) : ViewModelProvider.AndroidViewModelFactory(applicationLoader) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }

}