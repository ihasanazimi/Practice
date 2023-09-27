package ir.ha.practice.ui.tabs.components.mvvm_arch.live_data

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentMediatorLiveDataBinding
import ir.ha.practice.utility.base.BaseFragment

class MediatorLiveDataFragment : BaseFragment<FragmentMediatorLiveDataBinding>() {

    override val layoutId: Int get() = R.layout.fragment_mediator_live_data
    private lateinit var mediatorLiveDataChecker : MediatorLiveDataChecker


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediatorLiveDataChecker = ViewModelProvider(this)[MediatorLiveDataChecker::class.java]
        mediatorLiveDataChecker.startCheck()
        mediatorLiveDataChecker.mediatorLiveData.observe(this.viewLifecycleOwner){
           requireActivity().runOnUiThread{
               Log.i("mediatorLiveData -> ",  it)
           }
        }


    }
}