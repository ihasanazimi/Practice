package ir.ha.dummy.ui.sample.fragments.card

import android.os.Bundle
import android.view.View
import ir.ha.dummy.R
import ir.ha.dummy.databinding.SampleFrgCardBinding
import ir.ha.dummy.utility.base.BaseFragment

class CardFrg : BaseFragment<SampleFrgCardBinding>() {
    override val layoutId: Int get() = R.layout.sample_frg_card

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}