package ir.ha.dep.ui.fragment.mvvm_arch.live_data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentLiveDataBinding
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.showToast

class LiveDataFrg : BaseFragment() {


    lateinit var myLocationCheckerByLiveData: MyLocationCheckerByLiveData
    private lateinit var binding : FragmentLiveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myLocationCheckerByLiveData = MyLocationCheckerByLiveData()
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

        myLocationCheckerByLiveData.locationLiveData.observe(viewLifecycleOwner) {
            requireActivity().runOnUiThread{
                showToast(requireContext(),it)
            }
        }

    }
}