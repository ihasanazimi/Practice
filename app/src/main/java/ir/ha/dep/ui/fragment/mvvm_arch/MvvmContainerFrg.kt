package ir.ha.dep.ui.fragment.mvvm_arch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentMvvmContainerBinding
import ir.ha.dep.databinding.FragmentMvvmRxJavaBinding
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.ui.fragment.mvvm_arch.lifecycleObserver.LifecycleOwnerFrg
import ir.ha.dep.ui.fragment.mvvm_arch.live_data.LiveDataFrg

class MvvmContainerFrg : BaseFragment() {


    lateinit var binding : FragmentMvvmContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_mvvm_container,container!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLifecycleObserver.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , LifecycleOwnerFrg()).addToBackStack(LifecycleOwnerFrg().tag).commit()
        }


        binding.btnLiveData.setOnClickListener{
            childFragmentManager.beginTransaction().replace(R.id.mvvmContainerFrame , LiveDataFrg()).addToBackStack(LiveDataFrg().tag).commit()
        }
    }
}