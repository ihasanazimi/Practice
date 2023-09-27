package ir.ha.practice.ui.components.flow

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentFlowBinding
import ir.ha.practice.utility.base.BaseFragmentByVM
import kotlinx.coroutines.launch

class FlowFragment : BaseFragmentByVM<FragmentFlowBinding, FlowFragmentVM>() {
    override val layoutId: Int get() = R.layout.fragment_flow
    override val viewModel: FlowFragmentVM get() = ViewModelProvider(this)[FlowFragmentVM::class.java]

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