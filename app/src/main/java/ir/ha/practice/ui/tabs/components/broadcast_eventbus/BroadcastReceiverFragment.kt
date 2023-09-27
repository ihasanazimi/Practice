package ir.ha.practice.ui.tabs.components.broadcast_eventbus

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentBroadcastRecevierBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.isInternetConnected

class BroadcastReceiverFragment : BaseFragment<FragmentBroadcastRecevierBinding>() {
    override val layoutId: Int get() = R.layout.fragment_broadcast_recevier

    private lateinit var service : BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service = BroadCastSample()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNet(requireContext())
        requireActivity().registerReceiver(service, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    private fun checkNet(ctx : Context) {
        if (isInternetConnected(this@BroadcastReceiverFragment.requireContext()))
            binding.iv.setImageResource(R.drawable.ic_online)
        else binding.iv.setImageResource(R.drawable.ic_offline)
    }

    /** in class fazife ino dare k hamishe goosh bede be action change connection
     * va aqar taqir kard method onreceived ro call kone
     */
    inner class BroadCastSample : BroadcastReceiver(){
        override fun onReceive(context: Context?, p1: Intent?) { checkNet(context!!) }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unregisterReceiver(service)
    }
}