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

class SimpleLiveDataFrg : BaseFragment() {


    lateinit var simpleLiveDataChecker: SimpleLiveDataChecker
    private lateinit var binding : FragmentLiveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        simpleLiveDataChecker = SimpleLiveDataChecker()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_live_data,container!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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