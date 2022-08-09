package ir.ha.dep.ui.fragment.mvvm_arch.live_data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentMediatorLiveDataBinding
import ir.ha.dep.ui.BaseFragment

class MediatorLiveDataFrg : BaseFragment() {

    private lateinit var binding : FragmentMediatorLiveDataBinding
    private lateinit var mediatorLiveDataChecker : MediatorLiveDataChecker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_mediator_live_data,container!!)
        return binding.root
    }


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