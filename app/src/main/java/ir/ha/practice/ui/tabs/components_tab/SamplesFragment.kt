package ir.ha.practice.ui.tabs.components_tab

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentComponentsBinding
import ir.ha.practice.model.Components
import ir.ha.practice.model.adapters.ComponentsAdapter
import ir.ha.practice.model.adapters.DeveloperTagsAdapter
import ir.ha.practice.utility.base.BaseFragment

class SamplesFragment  : BaseFragment<FragmentComponentsBinding>(), View.OnClickListener {
    override val layoutId: Int get() = R.layout.fragment_components

    lateinit var componentsAdapter : ComponentsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentsAdapter = ComponentsAdapter()
        binding.rv.adapter = componentsAdapter
        componentsAdapter.setNewList(Components.sampleComponents)
    }

    override fun onClick(v: View?) {

    }



}