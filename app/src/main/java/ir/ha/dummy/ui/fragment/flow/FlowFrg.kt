package ir.ha.dummy.ui.fragment.flow

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentFlowBinding
import ir.ha.dummy.utility.base.BaseFragmentByVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FlowFrg : BaseFragmentByVM<FragmentFlowBinding,FlowVM>() {
    override val layoutId: Int get() = R.layout.fragment_flow
    override val viewModel: FlowVM get() = ViewModelProvider(this)[FlowVM::class.java]
    private lateinit var flow : Flow<Int>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFlow()
        lifecycleScope.launchWhenCreated {
            flow.collect{
                binding.tv.text = it.toString()
            }
        }

    }


    override fun registerObservers() {
        super.registerObservers()

    }

    fun setupFlow() {
        flow = flow<Int> {
            (0..10).forEach {
                delay(1000)
                Log.i("hsn", "setupFlow: " + "$it")
                emit(it)
            }
        }
            .map { it * 10 }
            .flowOn(Dispatchers.Main)
    }
}