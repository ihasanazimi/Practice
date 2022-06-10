package ir.ha.dep.ui.fragment.viewPager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentViewPagerSampleBinding
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.ui.viewModels.SampleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewPagerSampleFrg : BaseFragment() {

    private val viewModel by viewModel<SampleViewModel>()
    private lateinit var binding : FragmentViewPagerSampleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_view_pager_sample, container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBanners().observe(viewLifecycleOwner){banners->
            binding.viewPager.adapter = ViewPagerAdapter(this,banners)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Log.i("hsn", "Error LiveData: $it")
        }

    }
}