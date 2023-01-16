package ir.ha.practice.ui.main.fragment.fragment_types

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.ha.practice.databinding.DialogFragmentBottomSheetBinding

class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DialogFragmentBottomSheetBinding.inflate(LayoutInflater.from(requireContext()),null,false)
        return binding.root
    }

}