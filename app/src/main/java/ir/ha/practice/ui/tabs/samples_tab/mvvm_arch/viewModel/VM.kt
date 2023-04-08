package ir.ha.practice.ui.tabs.samples_tab.mvvm_arch.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ir.ha.practice.utility.base.BaseViewModel
import java.util.*


/**
 * ViewModel :
 *  dar vaqe komak mikone be negahdari etelaAt vagi masalan activity recreat mishe ya rotate mishe
 * baraye ijad yek viewModel bayad class ro az Class e ViewModel extend konim -> androidx.lifecycle.ViewModel

 * Shared ViewModel :
 * ine k unja k darim view model ro init mikonim (dakhel fragment ha)
 * owner hamashun ro yeki midim - va az taraf dg ham dakhel owner k ye activity hast ham..
 * view model ro initialize mikonim
 * va intori agar dakhel har fragment ha variable haye view model ro megdar dehi konim.. maqadiresh dg az beyn nemiran :)
 * va injori asl sahed view model beyn fragment ha ro beja avordim..
 * */

class VM : BaseViewModel()  {

    var counterNumber : Int = -1
    val timer = Timer()
    val counterMutableLiveData = MutableLiveData<Int>()

    fun startTimerOnViewModel() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                counterNumber++
                counterMutableLiveData.postValue(counterNumber)
                Log.i("counterTimer ","counterTimer -> $counterNumber")
            }
        } , 1000 , 1000)
    }


    override fun onCleared() {
        super.onCleared()
        timer.apply {
            cancel()
            purge()
        }
    }
}