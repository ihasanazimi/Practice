package ir.ha.practice.ui.tabs.test

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentTestBinding
import ir.ha.practice.utility.base.BaseFragment

class TestFragment : BaseFragment<FragmentTestBinding>() {
    override val layoutId: Int get() = R.layout.fragment_test

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}