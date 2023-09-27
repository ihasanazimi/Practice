package ir.ha.practice.ui.tabs.components.mvvm_arch.live_data.ui

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentLiveDataBinding
import ir.ha.practice.ui.components.mvvm_arch.live_data.LiveDataChecker
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast

class LiveDataFragment : BaseFragment<FragmentLiveDataBinding>() {
    override val layoutId: Int get() = R.layout.fragment_live_data

    lateinit var liveDataChecker: LiveDataChecker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveDataChecker = LiveDataChecker()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun registerObservers() {
        super.registerObservers()

        liveDataChecker.locationLiveData.observe(viewLifecycleOwner) {
            requireActivity().runOnUiThread{ showToast(requireContext(),it) }
        }

//        simpleLiveDataChecker.getFullLiveDataTitle().observe(viewLifecycleOwner){
//            requireActivity().runOnUiThread{
//                Snackbar.make(requireContext(),binding.snackBarContainer,it,Snackbar.LENGTH_INDEFINITE).show()
//            }
//        }
    }
}