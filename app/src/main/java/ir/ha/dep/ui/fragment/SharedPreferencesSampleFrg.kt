package ir.ha.dep.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentSharedPreferncesSampleBinding
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.showToast

class SharedPreferencesSampleFrg : BaseFragment() {

    private lateinit var binding : FragmentSharedPreferncesSampleBinding
    private lateinit var pref: SharedPreferences
    private val sharedPrefFileName  = "sharedPrefFileName"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = requireActivity().getSharedPreferences(sharedPrefFileName,Context.MODE_PRIVATE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = getBinding(R.layout.fragment_shared_prefernces_sample, container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // show default value from pref file
        binding.input.setText(pref.getString("test_key" , "متن پیش فرض"))

        // save | read
        binding.btn.setOnClickListener{

            if (binding.input.text.isEmpty()) binding.input.setText(pref.getString("test_Key" , "متن پیش فرض"))
            else {
                pref.edit().putString("test_key" , binding.input.text.toString().trimEnd().trimStart()).commit()
                binding.input.setText(binding.input.text.toString().trimEnd().trimStart())
                showToast(requireContext(), "متن ذخیره شد")
            }

        }

    }
}