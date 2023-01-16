package ir.ha.practice.ui.main.fragment.navigation_component

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import ir.ha.practice.R
import ir.ha.practice.databinding.Fragment3Binding
import ir.ha.practice.utility.base.BaseFragment

class Fragment3 : BaseFragment<Fragment3Binding>() {
    override val layoutId: Int get() = R.layout.fragment_3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextBtn.setOnClickListener{ Navigation.findNavController(it).navigate(R.id.action_frg3_to_frg1) }
    }
}