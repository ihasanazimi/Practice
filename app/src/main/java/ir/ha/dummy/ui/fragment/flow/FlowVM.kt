package ir.ha.dummy.ui.fragment.flow

import ir.ha.dummy.utility.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FlowVM : BaseViewModel() {

    var flow : Flow<Int> = flow {
        (0..10).forEach {
            delay(1000)
            emit(it)
        }
    }   .map { it * 10 }
        .flowOn(Dispatchers.Main)
    fun setupFlow() {

    }

}