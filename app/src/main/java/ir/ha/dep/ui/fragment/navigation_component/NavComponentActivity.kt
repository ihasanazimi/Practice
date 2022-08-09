package ir.ha.dep.ui.fragment.navigation_component

import android.os.Bundle
import androidx.navigation.Navigation
import ir.ha.dep.R
import ir.ha.dep.databinding.ActivityNavComponentBinding
import ir.ha.dep.ui.BaseActivity

class NavComponentActivity : BaseActivity() {

    private lateinit var binding : ActivityNavComponentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_nav_component)
        setContentView(binding.root)

        Navigation.findNavController(this,R.id.fragmentContainerView)
    }

}