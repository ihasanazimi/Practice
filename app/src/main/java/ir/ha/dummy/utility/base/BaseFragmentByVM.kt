package ir.ha.dummy.utility.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragmentByVM<V : ViewDataBinding , VM : BaseViewModel> : Fragment() {

    private var _binding: V? = null
    abstract var viewModel: VM

    val binding get() = _binding!!

    val mainHelper by lazy { (requireActivity()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModel.javaClass]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
    }

    open fun registerObservers(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @get:LayoutRes
    abstract val layoutId: Int

    open fun onScrollToTop() {}

    open fun onRetrievedTag(retrievedTag: String) {}
}