package ir.ha.dummy.ui.fragment.mvvm_arch.viewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentViewModelSampleBinding
import ir.ha.dummy.utility.base.BaseFragment

class SampleViewModelFrg : BaseFragment() {

    private lateinit var binding : FragmentViewModelSampleBinding
    private lateinit var viewModel : SampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SampleViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_view_model_sample,container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.startTimerOnViewModel()
        viewModel.counterMutableLiveData.observe(this.viewLifecycleOwner) {
            binding.counterTV.text = viewModel.counterNumber.toString()
        }

    }
}