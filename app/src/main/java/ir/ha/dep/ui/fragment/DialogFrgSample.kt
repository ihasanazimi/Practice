package ir.ha.dep.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import ir.ha.dep.databinding.DialogFragmentBinding

class DialogFrgSample : DialogFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val binding = DialogFragmentBinding.inflate(LayoutInflater.from(requireContext()),null,false)
        builder.setView(binding.root)
        return builder.create()
    }


}