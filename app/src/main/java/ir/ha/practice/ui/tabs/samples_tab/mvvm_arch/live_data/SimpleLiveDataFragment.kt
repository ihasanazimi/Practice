package ir.ha.practice.ui.tabs.samples_tab.mvvm_arch.live_data

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentLiveDataBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast

class SimpleLiveDataFragment : BaseFragment<FragmentLiveDataBinding>() {
    override val layoutId: Int get() = R.layout.fragment_live_data

    lateinit var simpleLiveDataChecker: SimpleLiveDataChecker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        simpleLiveDataChecker = SimpleLiveDataChecker()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun registerObservers() {
        super.registerObservers()

        simpleLiveDataChecker.locationLiveData.observe(viewLifecycleOwner) {
            requireActivity().runOnUiThread{ showToast(requireContext(),it) }
        }

        simpleLiveDataChecker.getFullLiveDataTitle().observe(viewLifecycleOwner){
            requireActivity().runOnUiThread{
                Snackbar.make(requireContext(),binding.snackBarContainer,it,Snackbar.LENGTH_INDEFINITE).show()
            }
        }
    }
}