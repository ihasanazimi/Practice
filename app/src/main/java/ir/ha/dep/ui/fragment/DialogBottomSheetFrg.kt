package ir.ha.dep.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.ha.dep.databinding.DialogFragmentBottomSheetBinding

class DialogBottomSheetFrg : BottomSheetDialogFragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DialogFragmentBottomSheetBinding.inflate(LayoutInflater.from(requireContext()),null,false)
        return binding.root
    }


}