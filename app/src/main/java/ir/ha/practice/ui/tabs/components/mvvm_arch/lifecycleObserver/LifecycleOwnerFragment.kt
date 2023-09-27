package ir.ha.practice.ui.tabs.components.mvvm_arch.lifecycleObserver

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentLifeCycleOwnerBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast

class LifecycleOwnerFragment : BaseFragment<FragmentLifeCycleOwnerBinding>(),
    MyLocationChecker.OnUpdate {

    override val layoutId: Int get() = R.layout.fragment_life_cycle_owner
    lateinit var  myLocationChecker : MyLocationChecker

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myLocationChecker = MyLocationChecker(this)
        // get life cycle AND set lifeCycle owner for this fragment
        lifecycle.addObserver(myLocationChecker)
    }

    override fun onLocation(location: String) {
        requireActivity().runOnUiThread{ showToast(requireContext(),location) }
    }
}