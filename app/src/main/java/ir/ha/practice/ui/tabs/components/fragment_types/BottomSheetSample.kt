package ir.ha.practice.ui.tabs.components.fragment_types

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.ha.practice.R
import ir.ha.practice.databinding.BottomSheetBinding
import ir.ha.practice.utility.base.BaseBottomSheet

class BottomSheetSample : BaseBottomSheet<BottomSheetBinding>() {
    override val layoutId: Int get() = R.layout.bottom_sheet


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}