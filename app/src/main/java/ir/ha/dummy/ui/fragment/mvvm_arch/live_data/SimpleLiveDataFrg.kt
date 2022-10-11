package ir.ha.dummy.ui.fragment.mvvm_arch.live_data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentLiveDataBinding
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.showToast

class SimpleLiveDataFrg : BaseFragment<FragmentLiveDataBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_live_data

    lateinit var simpleLiveDataChecker: SimpleLiveDataChecker

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        simpleLiveDataChecker = SimpleLiveDataChecker()

        simpleLiveDataChecker.locationLiveData.observe(viewLifecycleOwner) {
            requireActivity().runOnUiThread{
                showToast(requireContext(),it)
            }
        }

        simpleLiveDataChecker.getFullLiveDataTitle().observe(viewLifecycleOwner){
            requireActivity().runOnUiThread{
                Snackbar.make(requireContext(),binding.snackBarContainer,it,Snackbar.LENGTH_INDEFINITE).show()
            }
        }

    }
}