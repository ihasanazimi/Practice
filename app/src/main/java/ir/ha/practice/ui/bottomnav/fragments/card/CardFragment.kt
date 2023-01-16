package ir.ha.practice.ui.bottomnav.fragments.card

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.SampleFrgCardBinding
import ir.ha.practice.utility.base.BaseFragment

class CardFragment : BaseFragment<SampleFrgCardBinding>() {
    override val layoutId: Int get() = R.layout.sample_frg_card

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}