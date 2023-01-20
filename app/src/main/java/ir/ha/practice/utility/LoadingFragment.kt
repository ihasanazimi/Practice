package ir.ha.practice.utility

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.LoadingBarBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.onBackClick

class LoadingFragment : BaseFragment<LoadingBarBinding>() {
    override val layoutId: Int
        get() = R.layout.loading_bar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        this.onBackClick {
            return@onBackClick
        }
    }
}