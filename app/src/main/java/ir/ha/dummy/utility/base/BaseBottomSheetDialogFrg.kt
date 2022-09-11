package ir.ha.dummy.utility.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFrg: BottomSheetDialogFragment() , BaseView {

    override val rootView: ViewGroup?
        get() = dialog?.window?.decorView?.parent as ViewGroup
    override val viewContext: Context?
        get() = this.requireContext()

    fun <T : ViewDataBinding?> getBinding(layout: Int): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layout, null, false)
    }

}