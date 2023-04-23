package ir.ha.practice.ui.tabs.components_tab.fragment_types

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow


class SharedViewModel() : ViewModel() {

    var dataFragment1 = MutableStateFlow<String>("")
    var dataFragment2 = MutableStateFlow<String>("")

}