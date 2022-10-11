package ir.ha.dummy.ui.fragment.navigation_component

import android.os.Bundle
import androidx.navigation.Navigation
import ir.ha.dummy.R
import ir.ha.dummy.databinding.ActivityNavComponentBinding
import ir.ha.dummy.utility.base.BaseActivity

class NavComponentActivity : BaseActivity<ActivityNavComponentBinding>() {
    override val layoutId: Int get() = R.layout.activity_nav_component

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigation.findNavController(this,R.id.fragmentContainerView)
    }

}