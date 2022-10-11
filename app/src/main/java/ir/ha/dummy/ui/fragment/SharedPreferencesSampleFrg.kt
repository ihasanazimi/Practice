package ir.ha.dummy.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentSharedPreferncesSampleBinding
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.showToast

class SharedPreferencesSampleFrg : BaseFragment<FragmentSharedPreferncesSampleBinding>() {
    override val layoutId: Int get() = R.layout.fragment_shared_prefernces_sample
    lateinit var pref: SharedPreferences
    private val sharedPrefFileName  = "sharedPrefFileName"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireActivity().getSharedPreferences(sharedPrefFileName,Context.MODE_PRIVATE)
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