package ir.ha.practice.ui.tabs.components.fragment_types

import android.os.Bundle
import android.util.Log
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.BottomSheetBinding
import ir.ha.practice.utility.base.BaseBottomSheet

class BottomSheetSample : BaseBottomSheet<BottomSheetBinding>() {
    override val layoutId: Int get() = R.layout.bottom_sheet
    val TAG = BottomSheetSample::class.java.simpleName


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ")
    }

}