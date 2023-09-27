package ir.ha.practice.ui.components.mvvm_arch.lifecycleObserver

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.util.*

class MyLocationChecker(val callBack : OnUpdate) : LifecycleObserver {

     var timer = Timer()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startCheck() {
        timer.schedule(object  : TimerTask() {
            override fun run() { callBack.onLocation("this is user location") }
                                             } , 1000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopCheck()  { timer.cancel() }

    interface OnUpdate{ fun onLocation(location : String) }
}