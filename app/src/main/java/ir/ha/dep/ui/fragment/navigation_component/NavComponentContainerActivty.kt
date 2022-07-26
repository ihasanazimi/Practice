package ir.ha.dep.ui.fragment.navigation_component

import android.os.Bundle
import ir.ha.dep.R
import ir.ha.dep.databinding.ActivityNavComponentBinding
import ir.ha.dep.ui.BaseActivity

class NavComponentContainerActivty : BaseActivity() {

    private lateinit var binding : ActivityNavComponentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_nav_component)
        setContentView(binding.root)
    }

}