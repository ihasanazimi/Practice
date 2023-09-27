package ir.ha.practice.ui.components.fragment_types.dialog

import android.os.Bundle
import android.util.Log
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.DialogFragmentBinding
import ir.ha.practice.utility.base.BaseDialog

class DialogSample : BaseDialog<DialogFragmentBinding>() {
    override val layoutId: Int get() = R.layout.dialog_fragment
    val TAG = DialogSample::class.java.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ")
    }


}