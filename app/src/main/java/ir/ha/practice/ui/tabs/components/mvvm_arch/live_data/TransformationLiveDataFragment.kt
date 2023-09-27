package ir.ha.practice.ui.tabs.components.mvvm_arch.live_data

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentTransformationLiveDataBinding
import ir.ha.practice.utility.base.BaseFragment

class TransformationLiveDataFragment : BaseFragment<FragmentTransformationLiveDataBinding>(){
    override val layoutId: Int get() = R.layout.fragment_transformation_live_data

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}