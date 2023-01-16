package ir.ha.practice.ui.main.fragment.navigation_component

import android.os.Bundle
import androidx.navigation.Navigation
import ir.ha.practice.R
import ir.ha.practice.databinding.ActivityNavComponentBinding
import ir.ha.practice.utility.base.BaseActivity

class NavComponentActivity : BaseActivity<ActivityNavComponentBinding>() {
    override val layoutId: Int get() = R.layout.activity_nav_component

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigation.findNavController(this,R.id.fragmentContainerView)
    }

}