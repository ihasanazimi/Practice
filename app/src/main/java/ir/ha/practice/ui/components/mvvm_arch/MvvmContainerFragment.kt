package ir.ha.practice.ui.components.mvvm_arch

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentMvvmContainerBinding
import ir.ha.practice.ui.components.mvvm_arch.lifecycleObserver.LifecycleOwnerFragment
import ir.ha.practice.ui.tabs.components.mvvm_arch.live_data.ui.MediatorLiveDataFragment
import ir.ha.practice.ui.tabs.components.mvvm_arch.live_data.ui.LiveDataFragment
import ir.ha.practice.ui.components.mvvm_arch.viewModel.ViewModelFragment
import ir.ha.practice.utility.base.BaseFragment

class MvvmContainerFragment : BaseFragment<FragmentMvvmContainerBinding>() {

    override val layoutId: Int get() = R.layout.fragment_mvvm_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun registerListeners() {
        super.registerListeners()
        binding.btnLifecycleObserver.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , LifecycleOwnerFragment()).addToBackStack(
                LifecycleOwnerFragment().tag).commit()
        }


        binding.btnLiveData.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , LiveDataFragment()).addToBackStack(
                LiveDataFragment().tag).commit()
        }

        binding.btnViewModel.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , ViewModelFragment()).addToBackStack(
                ViewModelFragment().tag).commit()
        }


        binding.btnMediatorLiveData.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , MediatorLiveDataFragment()).addToBackStack(
                MediatorLiveDataFragment().tag).commit()
        }
    }
}