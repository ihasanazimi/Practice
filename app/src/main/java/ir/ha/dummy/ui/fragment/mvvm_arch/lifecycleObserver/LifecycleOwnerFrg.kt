package ir.ha.dummy.ui.fragment.mvvm_arch.lifecycleObserver

import android.os.Bundle
import android.view.View
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentLifeCycleOwnerBinding
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.showToast

class LifecycleOwnerFrg : BaseFragment<FragmentLifeCycleOwnerBinding>(), MyLocationChecker.OnUpdate {

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