package ir.ha.dummy.ui.fragment.navigation_component

import android.os.Bundle
import androidx.navigation.Navigation
import ir.ha.dummy.R
import ir.ha.dummy.databinding.ActivityNavComponentBinding
import ir.ha.dummy.utility.base.BaseActivity

class NavComponentActivity : BaseActivity() {

    private lateinit var binding : ActivityNavComponentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_nav_component)
        setContentView(binding.root)

        Navigation.findNavController(this,R.id.fragmentContainerView)
    }

}