package ir.ha.practice.ui.components.flow

import androidx.lifecycle.viewModelScope
import ir.ha.practice.utility.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowFragmentVM : BaseViewModel() {

    private var _statFlow = MutableStateFlow(0)
    val stateFlow = _statFlow.asStateFlow()

    private var _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()


    fun stateFlow () : Flow<Int>{
        viewModelScope.launch {
            var sec = 0
            val finish = 0
            while (sec <= finish){
                delay(1000)
                sec=sec++
                _statFlow.emit(sec)
            }
        }
        return _statFlow
    }


    fun sharedFlow () : Flow<Int>{
        val counter = 0
        val arr = arrayListOf("A","B","C","D","E","F","G","H","I")
        repeat(arr.size) {
            viewModelScope.launch {
                while (counter <= arr.size){
                    delay(1000)
                    _sharedFlow.emit(it)
                }
            }
        }
        return _sharedFlow
    }

}