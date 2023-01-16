package ir.ha.practice.ui.main.fragment.mvvm_arch

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentMvvmContainerBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.ui.main.fragment.mvvm_arch.lifecycleObserver.LifecycleOwnerFragment
import ir.ha.practice.ui.main.fragment.mvvm_arch.live_data.SimpleLiveDataFragment
import ir.ha.practice.ui.main.fragment.mvvm_arch.live_data.MediatorLiveDataFragment
import ir.ha.practice.ui.main.fragment.mvvm_arch.viewModel.SampleViewModelFrg

class MvvmContainerFrg : BaseFragment<FragmentMvvmContainerBinding>() {

    override val layoutId: Int get() = R.layout.fragment_mvvm_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLifecycleObserver.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , LifecycleOwnerFragment()).addToBackStack(LifecycleOwnerFragment().tag).commit()
        }


        binding.btnLiveData.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , SimpleLiveDataFragment()).addToBackStack(SimpleLiveDataFragment().tag).commit()
        }

        binding.btnViewModel.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , SampleViewModelFrg()).addToBackStack(SampleViewModelFrg().tag).commit()
        }


        binding.btnMediatorLiveData.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , MediatorLiveDataFragment()).addToBackStack(MediatorLiveDataFragment().tag).commit()
        }
    }
}