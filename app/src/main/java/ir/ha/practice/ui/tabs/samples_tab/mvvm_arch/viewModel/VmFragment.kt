package ir.ha.practice.ui.tabs.samples_tab.mvvm_arch.viewModel

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentViewModelBinding
import ir.ha.practice.utility.base.BaseFragmentByVM

class VmFragment : BaseFragmentByVM<FragmentViewModelBinding, VM>() {
    override val layoutId: Int get() = R.layout.fragment_view_model
    override val viewModel get() = ViewModelProvider(this)[VM::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startTimerOnViewModel()
    }

    override fun registerObservers() {
        super.registerObservers()
        viewModel.counterMutableLiveData.observe(this.viewLifecycleOwner) {
            binding.counterTV.text = viewModel.counterNumber.toString()
        }
    }
}