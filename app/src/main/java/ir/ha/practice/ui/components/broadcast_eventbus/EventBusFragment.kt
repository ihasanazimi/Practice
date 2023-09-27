package ir.ha.practice.ui.components.broadcast_eventbus

import android.os.Bundle
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentEventBusBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusFragment : BaseFragment<FragmentEventBusBinding>() {
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