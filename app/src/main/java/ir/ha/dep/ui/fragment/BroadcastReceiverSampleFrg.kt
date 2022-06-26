package ir.ha.dep.ui.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentBroadCastRecevierBinding
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.checkInternetConnection

class BroadcastReceiverSampleFrg : BaseFragment() {

    private lateinit var binding: FragmentBroadCastRecevierBinding
    private lateinit var service : BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_broad_cast_recevier, container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service = BroadCastSample()
        checkNet(requireContext())
        requireActivity().registerReceiver(service, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    private fun checkNet(ctx : Context) {
        if (checkInternetConnection(this@BroadcastReceiverSampleFrg.requireContext()))
            binding.tv.text = "network is -->  Connected"
        else binding.tv.text = "network is -->  DisConnected..!"
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