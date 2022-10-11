package ir.ha.dummy.ui.fragment.navigation_component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ir.ha.dummy.R
import ir.ha.dummy.databinding.Fragment3Binding
import ir.ha.dummy.utility.base.BaseFragment

class Frg3 : BaseFragment<Fragment3Binding>() {

    override val layoutId: Int
        get() = R.layout.fragment_3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_frg3_to_frg1)
        }
    }
}