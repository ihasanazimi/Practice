package ir.ha.dummy.ui.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentBroadCastRecevierBinding
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.checkInternetConnection

class BroadcastReceiverSampleFrg : BaseFragment<FragmentBroadCastRecevierBinding>() {
    override val layoutId: Int get() = R.layout.fragment_broad_cast_recevier

    private lateinit var service : BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service = BroadCastSample()
        checkNet(requireContext())
        requireActivity().registerReceiver(service, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    private fun checkNet(ctx : Context) {
        if (checkInternetConnection(this@BroadcastReceiverSampleFrg.requireContext()))
            binding.iv.setImageResource(R.drawable.ic_online)
        else binding.iv.setImageResource(R.drawable.ic_offline)
    }


    /** in class fazife ino dare k hamishe goosh bede be action change connection
     * va aqar taqir kard method onreceived ro call kone
     */
    inner class BroadCastSample : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            checkNet(p0!!)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unregisterReceiver(service)
    }
}