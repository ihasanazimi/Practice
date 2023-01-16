package ir.ha.practice.ui.bottomnav.fragments.home

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.SampleFrgHomeBinding
import ir.ha.practice.utility.base.BaseFragment

class HomeFragment: BaseFragment<SampleFrgHomeBinding>() {
    override val layoutId: Int get() = R.layout.sample_frg_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}