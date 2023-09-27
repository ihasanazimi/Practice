package ir.ha.practice.utility.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import ir.ha.practice.utility.extentions.showToast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragmentByVM<V : ViewDataBinding , VM : BaseViewModel> : Fragment() {

    abstract val viewModel: VM
    private var _binding: V? = null
    val binding get() = _binding!!
    @get:LayoutRes
    abstract val layoutId: Int
    val mainHelper by lazy { (requireActivity()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerClickListeners()
        registerObservers()
    }

    open fun registerObservers(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errorFlow.collectLatest {
                showToast(requireContext(), it)
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            if (it.size != 0) showToast(requireContext(), it.first())
        }
    }

    open fun registerClickListeners(){}


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel.clearErrorLiveData()
    }

}