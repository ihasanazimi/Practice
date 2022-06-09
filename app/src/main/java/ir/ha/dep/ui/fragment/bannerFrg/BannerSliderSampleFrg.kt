package ir.ha.dep.ui.fragment.bannerFrg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dep.R
import ir.ha.dep.databinding.BannerSliderFragmentBinding
import ir.ha.dep.feacher.BaseFragment
import ir.ha.dep.model.adapters.BannerAdapter
import ir.ha.dep.ui.viewModels.SampleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BannerSliderSampleFrg : BaseFragment() {

    private val viewModel by viewModel<SampleViewModel>()
    private lateinit var binding : BannerSliderFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.banner_slider_fragment, container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBanners().observe(viewLifecycleOwner){banners->
            binding.viewPager.adapter = BannerAdapter(this,banners)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Log.i("hsn", "Error LiveData: $it")
        }

    }
}