package ir.ha.practice.ui.tabs.components.flow

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentFlowBinding
import ir.ha.practice.utility.base.BaseFragmentByVM
import kotlinx.coroutines.launch

class FlowFragment : BaseFragmentByVM<FragmentFlowBinding, FlowVM>() {
    override val layoutId: Int get() = R.layout.fragment_flow
    override val viewModel: FlowVM get() = ViewModelProvider(this)[FlowVM::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            stateFlow()
            sharedFlow()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateFlow.collect{
                binding.tvStateFlow.text = "stateFlow is : $it"
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sharedFlow.collect{
                binding.tvSharedFlow.text = "sharedFlow is : " + it
            }
        }
    }

}