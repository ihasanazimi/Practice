package ir.soleymani.clinic.utility.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.ha.practice.utility.base.BaseView

abstract class BaseBottomSheetDialogFragment<V : ViewDataBinding>: BottomSheetDialogFragment() , BaseView {

    private var _binding: V? = null
    val binding get() = _binding!!
    val mainHelper by lazy { (requireActivity()) }

    override val rootView: ViewGroup? get() = dialog?.window?.decorView?.parent as ViewGroup
    override val viewContext: Context? get() = this.requireContext()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
    }

    open fun registerObservers() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @get:LayoutRes
    abstract val layoutId: Int

    open fun onScrollToTop() {}

    open fun onRetrievedTag(retrievedTag: String) {}


}