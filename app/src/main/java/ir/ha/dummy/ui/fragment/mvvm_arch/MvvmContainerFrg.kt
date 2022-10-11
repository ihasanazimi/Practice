package ir.ha.dummy.ui.fragment.mvvm_arch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentMvvmContainerBinding
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.ui.fragment.mvvm_arch.lifecycleObserver.LifecycleOwnerFrg
import ir.ha.dummy.ui.fragment.mvvm_arch.live_data.SimpleLiveDataFrg
import ir.ha.dummy.ui.fragment.mvvm_arch.live_data.MediatorLiveDataFrg
import ir.ha.dummy.ui.fragment.mvvm_arch.viewModel.SampleViewModelFrg

class MvvmContainerFrg : BaseFragment<FragmentMvvmContainerBinding>() {

    override val layoutId: Int get() = R.layout.fragment_mvvm_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLifecycleObserver.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , LifecycleOwnerFrg()).addToBackStack(LifecycleOwnerFrg().tag).commit()
        }


        binding.btnLiveData.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , SimpleLiveDataFrg()).addToBackStack(SimpleLiveDataFrg().tag).commit()
        }

        binding.btnViewModel.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , SampleViewModelFrg()).addToBackStack(SampleViewModelFrg().tag).commit()
        }


        binding.btnMediatorLiveData.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , MediatorLiveDataFrg()).addToBackStack(MediatorLiveDataFrg().tag).commit()
        }
    }
}