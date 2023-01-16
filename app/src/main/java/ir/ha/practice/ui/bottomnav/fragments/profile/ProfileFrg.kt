package ir.ha.practice.ui.bottomnav.fragments.profile

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.SampleFrgProfileBinding
import ir.ha.practice.utility.base.BaseFragment

class ProfileFrg : BaseFragment<SampleFrgProfileBinding>() {
    override val layoutId: Int get() = R.layout.sample_frg_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}