package ir.ha.practice.ui.tabs.components.fragment_types

import androidx.lifecycle.ViewModel
import ir.ha.practice.utility.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow


class SharedViewModel() : BaseViewModel() {

    var dataFragment1 = MutableStateFlow<String>("")
    var dataFragment2 = MutableStateFlow<String>("")

}