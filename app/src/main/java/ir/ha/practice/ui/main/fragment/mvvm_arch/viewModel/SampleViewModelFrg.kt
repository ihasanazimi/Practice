package ir.ha.practice.ui.main.fragment.mvvm_arch.viewModel

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentViewModelSampleBinding
import ir.ha.practice.utility.base.BaseFragment

class SampleViewModelFrg : BaseFragment<FragmentViewModelSampleBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_view_model_sample


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[SampleViewModel::class.java]
        viewModel.startTimerOnViewModel()
        viewModel.counterMutableLiveData.observe(this.viewLifecycleOwner) {
            binding.counterTV.text = viewModel.counterNumber.toString()
        }

    }
}