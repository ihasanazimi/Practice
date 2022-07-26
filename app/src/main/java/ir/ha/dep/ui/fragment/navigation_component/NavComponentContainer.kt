package ir.ha.dep.ui.fragment.navigation_component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dep.R
import ir.ha.dep.databinding.Fragment1Binding
import ir.ha.dep.databinding.Fragment3Binding
import ir.ha.dep.databinding.FragmentNavComponentBinding
import ir.ha.dep.ui.BaseFragment

class NavComponentContainer : BaseFragment() {

    private lateinit var binding : FragmentNavComponentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(R.layout.fragment_nav_component,container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}