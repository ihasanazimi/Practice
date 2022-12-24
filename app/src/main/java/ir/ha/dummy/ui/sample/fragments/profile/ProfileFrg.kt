package ir.ha.dummy.ui.sample.fragments.profile

import android.os.Bundle
import android.view.View
import ir.ha.dummy.R
import ir.ha.dummy.databinding.SampleFrgProfileBinding
import ir.ha.dummy.utility.base.BaseFragment

class ProfileFrg : BaseFragment<SampleFrgProfileBinding>() {
    override val layoutId: Int get() = R.layout.sample_frg_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}