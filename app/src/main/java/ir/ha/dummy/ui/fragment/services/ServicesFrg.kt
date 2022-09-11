package ir.ha.dummy.ui.fragment.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentServicesBinding
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.showToast

class ServicesFrg : BaseFragment(), View.OnClickListener {


    private lateinit var binding : FragmentServicesBinding
    private lateinit var backgroundIntent : Intent
    private lateinit var foregroundIntent : Intent
    private lateinit var boundIntent : Intent
    private var conn : ServiceConnection ? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  getBinding(R.layout.fragment_services,container!!)
        return binding.root
    }


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

        conn = object : ServiceConnection{
            override fun onServiceConnected(comName: ComponentName?, service : IBinder?) {
                val myBinder = service as BoundService.Companion.MyBinder
                val serviceInstance  =  myBinder.getServiceInstance()
                showToast(requireContext(),serviceInstance.getTestMessage())
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                conn = null
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
                    requireActivity().bindService(this,conn!! , Context.BIND_AUTO_CREATE)
                    binding.btnStopService.isEnabled = true
                    binding.btnBackgroundService.isEnabled = false
                    binding.btnForegroundService.isEnabled = false
                }
            }

            R.id.btn_stop_service ->{
                requireActivity().stopService(backgroundIntent)
                requireActivity().stopService(foregroundIntent)
//                requireActivity().unbindService(conn!!)

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
//        conn.let {
//            requireActivity().unbindService(conn!!)
//        }
    }


}