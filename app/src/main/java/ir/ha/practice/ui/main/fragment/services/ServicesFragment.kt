package ir.ha.practice.ui.main.fragment.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentServicesBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast

class ServicesFragment : BaseFragment<FragmentServicesBinding>(), View.OnClickListener {
    override val layoutId: Int get() = R.layout.fragment_services

    private lateinit var backgroundIntent : Intent
    private lateinit var foregroundIntent : Intent
    private lateinit var boundIntent : Intent
    private var serviceConnection : ServiceConnection ? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStopService.isEnabled = false
        binding.btnBackgroundService.setOnClickListener(this)
        binding.btnForegroundService.setOnClickListener(this)
        binding.btnBoundService.setOnClickListener(this)
        binding.btnStopService.setOnClickListener(this)
        backgroundIntent = Intent(requireContext(), BackgroundService::class.java)
        foregroundIntent = Intent(requireContext(), ForegroundService::class.java)
        boundIntent = Intent(requireContext(), BoundService::class.java)
    }

    override fun onClick(v: View?) {
        serviceConnection = object : ServiceConnection{
            override fun onServiceConnected(comName: ComponentName?, service : IBinder?) {
                val myBinder = service as BoundService.Companion.MyBinder
                val serviceInstance  =  myBinder.getServiceInstance()
                showToast(requireContext(),serviceInstance.getTestMessage())
            }
            override fun onServiceDisconnected(p0: ComponentName?) {
                serviceConnection = null
            }
        }

        when(v?.id){
            R.id.btn_background_service ->{
                backgroundIntent.apply {
                    putExtra("key", "background_service")
                    requireActivity().startService(this)
                    binding.btnStopService.isEnabled = true
                    binding.btnForegroundService.isEnabled = false
                    binding.btnBoundService.isEnabled = false
                }
            }
            R.id.btn_foreground_service ->{
                foregroundIntent.apply {
                    putExtra("key", "foreground_service")
                    requireActivity().startService(this)
                    binding.btnStopService.isEnabled = true
                    binding.btnBackgroundService.isEnabled = false
                    binding.btnBoundService.isEnabled = false
                }
            }
            R.id.btn_bound_service ->{
                boundIntent.apply {
                    putExtra("key", "bound_service")
                    requireActivity().bindService(this,serviceConnection!! , Context.BIND_AUTO_CREATE)
                    binding.btnStopService.isEnabled = true
                    binding.btnBackgroundService.isEnabled = false
                    binding.btnForegroundService.isEnabled = false
                }
            }

            R.id.btn_stop_service ->{
                requireActivity().stopService(backgroundIntent)
                requireActivity().stopService(foregroundIntent)
                /* requireActivity().unbindService(conn!!) */
                binding.btnBackgroundService.isEnabled = true
                binding.btnForegroundService.isEnabled = true
                binding.btnBoundService.isEnabled = true
                binding.btnStopService.isEnabled = false
            }
            else ->{
                showToast(requireContext(),"unKnow error else expression")
                binding.btnStopService.isEnabled = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
         /* serviceConnection.let { requireActivity().unbindService(serviceConnection!!) } */
    }

}