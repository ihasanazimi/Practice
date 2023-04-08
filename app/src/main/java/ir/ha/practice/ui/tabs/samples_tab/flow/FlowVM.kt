package ir.ha.practice.ui.tabs.samples_tab.flow

import androidx.lifecycle.viewModelScope
import ir.ha.practice.utility.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowVM : BaseViewModel() {

    var statFlow = MutableStateFlow<Int>(0)

    private var _sharedFlow = MutableSharedFlow<ArrayList<String>>()
    val sharedFlow = _sharedFlow.asSharedFlow()


    fun stateFlow () : Flow<Int>{
        viewModelScope.launch {
            var sec = 0
            delay(1000)
            sec++
            statFlow.emit(sec)
        }
        return statFlow
    }


    fun sharedFlow () : Flow<ArrayList<String>>{
        val arr = arrayListOf<String>("A" , "B" , "C")
        arr.forEach{
            viewModelScope.launch {
                delay(1000)
                _sharedFlow.emit(arr)
            }
        }
        return _sharedFlow
    }

}