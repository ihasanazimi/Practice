package ir.ha.dep.ui.fragment.mvvm_arch.live_data

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import java.util.*

class MyLocationCheckerByLiveData {

     var timer = Timer()
    val locationLiveData = MutableLiveData<String>()

    init {
        startCheck()
    }


    fun startCheck() {
        timer.schedule(object  : TimerTask() {
            override fun run() {
                locationLiveData.postValue("this is user location")
            } } , 1000)
    }

}