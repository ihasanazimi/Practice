package ir.ha.practice.ui.tabs.components_tab.fragment_types

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentBase1Binding
import ir.ha.practice.utility.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BaseFragment1 : BaseFragment<FragmentBase1Binding>() {
    override val layoutId: Int get() = R.layout.fragment_base1
    private val viewModel: SharedViewModel by viewModels()

    companion object{
        const val data = "this is data from fragment_1"
    }

    fun newInstance(message : String):BaseFragment1 {
        val args = Bundle()
        val fragment = BaseFragment1()
        fragment.arguments = args
        return fragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.dataFragment1.collect{
                    binding.messageTv.text = it
                }
            }
        }
    }

}