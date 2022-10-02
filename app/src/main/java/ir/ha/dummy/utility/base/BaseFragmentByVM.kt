package ir.ha.dummy.utility.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import ir.ha.dummy.utility.extentions.showToast

abstract class BaseFragmentByVM<V : ViewDataBinding , VM : BaseViewModel> : Fragment() {

    private var _binding: V? = null
    abstract val viewModel: VM

    val binding get() = _binding!!

    val mainHelper by lazy { (requireActivity()) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
    }

    open fun registerObservers(){

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            if (it.size != 0) showToast(requireContext(),it.first())
        }

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