package ir.ha.practice.utility.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import ir.ha.practice.R
import ir.ha.practice.utility.SnackBarUtils
import java.lang.ref.WeakReference

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    private var _binding: V? = null
    val binding get() = _binding!!
    @get:LayoutRes
    abstract val layoutId: Int
    val mainHelper by lazy { (requireActivity()) }
    private val TAG = BaseFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerListeners()
        registerObservers()
    }


    open fun registerObservers(){}
    open fun registerListeners(){}

    fun showErrorMessage(message: String) {
        Log.e(TAG, "showErrorMessage - $message")
        SnackBarUtils.showSnackBar(
            WeakReference(requireActivity()), message, R.drawable.baseline_error_outline_24
        )
    }

    fun showMessage(message: String, icon: Int = R.drawable.baseline_done_24) {
        Log.e("TAG", "showMessage: ")
        SnackBarUtils.showSnackBar(
            WeakReference(requireActivity()), message, icon
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}