package ir.ha.practice.ui.tabs.developer_tab

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentDeveloperBinding
import ir.ha.practice.utility.base.BaseFragment

class DeveloperFragment : BaseFragment<FragmentDeveloperBinding>() {
    override val layoutId: Int get() = R.layout.fragment_developer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}