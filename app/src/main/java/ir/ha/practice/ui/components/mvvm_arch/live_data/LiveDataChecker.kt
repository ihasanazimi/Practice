package ir.ha.practice.ui.components.mvvm_arch.live_data

import androidx.lifecycle.*
import ir.ha.practice.model.entities.UserEntity
import java.util.*

class LiveDataChecker {

    private var timer = Timer()
    private val user1 = UserEntity("body", 7, "Hsn Title", 66)
    private val user2 = UserEntity("body", 12, "omid Title", 66)

    val locationLiveData = MutableLiveData<String>()

    init {
        startCheck()
    }

    private fun startCheck() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                locationLiveData.postValue("simple liveData - > test")
            }
        }, 1000)
    }

}