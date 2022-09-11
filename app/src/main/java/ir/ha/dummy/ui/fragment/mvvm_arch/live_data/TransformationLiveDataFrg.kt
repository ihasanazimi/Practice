package ir.ha.dummy.ui.fragment.mvvm_arch.live_data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentTransformationLiveDataBinding
import ir.ha.dummy.utility.base.BaseFragment

class TransformationLiveDataFrg : BaseFragment(){

    private lateinit var binding :  FragmentTransformationLiveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_transformation_live_data,container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}