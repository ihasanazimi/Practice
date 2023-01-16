package ir.ha.practice.ui.main.fragment.mvvm_arch.live_data

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MediatorLiveDataChecker : ViewModel() {

     var timer1 = Timer()
     var timer2 = Timer()
    val liveData1 = MutableLiveData<String>()
    val liveData2 = MutableLiveData<String>()
    val mediatorLiveData = MediatorLiveData<String>()

     fun startCheck() {
        timer1.schedule(object  : TimerTask() {
            override fun run() { liveData1.postValue("liveData 1") } } , 1000 , 1000)

        timer2.schedule(object  : TimerTask() { override fun run() {
                liveData2.postValue("liveData 2") } } , 1000 , 1000)



        mediatorLiveData.apply { addSource(liveData1){mediatorLiveData.value = it} }

         mediatorLiveData.apply { addSource(liveData2){mediatorLiveData.value = it} }

    }

}