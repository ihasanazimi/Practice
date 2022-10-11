package ir.ha.dummy.ui.fragment

import android.os.Bundle
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentEventBusBinding
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.showToast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusFrg : BaseFragment<FragmentEventBusBinding>() {

    override val layoutId: Int get() = R.layout.fragment_event_bus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getMyEvent(myEvent: MyEvent){
        showToast(requireContext(),myEvent.message)
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}



class MyEvent(val message : String = "Hellooooo Event Bus")