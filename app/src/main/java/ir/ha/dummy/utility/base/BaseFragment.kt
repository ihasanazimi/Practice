package ir.ha.dummy.utility.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), BaseView {
    override val rootView: ViewGroup?
        get() = view as ViewGroup?
    override val viewContext: Context?
        get() = context


    // default
    fun <T : ViewDataBinding> getBinding(layoutID: Int, parent : ViewGroup): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layoutID , parent , false)
    }

    // for custom views
    fun <T : ViewDataBinding> getBinding(layoutID: Int, parent : ViewGroup, attachToRoot : Boolean): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layoutID , parent ,attachToRoot)
    }
}