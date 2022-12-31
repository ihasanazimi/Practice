package ir.ha.dummy.ui.fragment.flow

import ir.ha.dummy.utility.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowVM : BaseViewModel() {

    var myVal = 10
    val flow = flow<Int> {
        emit(myVal)
        delay(1000)
        myVal--
        emit(myVal)
    }

}