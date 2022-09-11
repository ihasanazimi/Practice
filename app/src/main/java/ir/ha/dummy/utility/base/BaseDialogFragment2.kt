package ir.ha.dummy.utility.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment2 : DialogFragment() , BaseView {

    override val rootView: ViewGroup?
        get() = dialog?.window?.decorView?.rootView as ViewGroup
    override val viewContext: Context?
        get() = this.requireContext()

    @SuppressLint("UseGetLayoutInflater")
    fun <T : ViewDataBinding?> getBinding(layout: Int): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layout, null, false)
    }

}