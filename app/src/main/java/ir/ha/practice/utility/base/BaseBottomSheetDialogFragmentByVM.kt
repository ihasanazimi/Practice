package ir.ha.practice.utility.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.ha.practice.utility.extentions.showToast

abstract class BaseBottomSheetDialogFragmentByVM<V : ViewDataBinding, VM : BaseViewModel>: BottomSheetDialogFragment()  {

    abstract val viewModel: VM
    private var _binding: V? = null
    val binding get() = _binding!!
    val mainHelper by lazy { (requireActivity()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        clickEvents()
    }

    open fun registerObservers() {

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            if (it.size != 0) showToast(requireContext(),it.first())
        }

    }

    open fun clickEvents(){}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.clearErrorLiveData()
    }

    @get:LayoutRes
    abstract val layoutId: Int

    open fun onScrollToTop() {}

    open fun onRetrievedTag(retrievedTag: String) {}


}