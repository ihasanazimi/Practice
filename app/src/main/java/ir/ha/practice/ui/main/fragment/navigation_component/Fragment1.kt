package ir.ha.practice.ui.main.fragment.navigation_component

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import ir.ha.practice.R
import ir.ha.practice.databinding.Fragment1Binding
import ir.ha.practice.utility.base.BaseFragment

class Fragment1 : BaseFragment<Fragment1Binding>() {
    override val layoutId: Int get() = R.layout.fragment_1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextBtn.setOnClickListener{ Navigation.findNavController(it).navigate(R.id.action_frg1_to_frg2) }
    }
}