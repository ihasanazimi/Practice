package ir.ha.dummy.ui.sample.fragments.home

import android.os.Bundle
import android.view.View
import ir.ha.dummy.R
import ir.ha.dummy.databinding.SampleFrgHomeBinding
import ir.ha.dummy.utility.base.BaseFragment

class HomeFrg: BaseFragment<SampleFrgHomeBinding>() {
    override val layoutId: Int get() = R.layout.sample_frg_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}