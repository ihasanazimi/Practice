package ir.ha.dep.feacher.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ir.ha.dep.R
import ir.ha.dep.feacher.BaseFragment
import ir.ha.dep.feacher.main.SampleViewModel
import ir.ha.dep.model.Banner
import ir.ha.dep.model.adapters.BannerAdapter
import kotlinx.android.synthetic.main.fragment_sample.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SampleFragment : BaseFragment(), BannerAdapter.BannersEventListener {

    private val viewModel by viewModel<SampleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_sample,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Log.i("hsn", "onViewCreated: $it")
        }

        viewModel.getBanners().observe(viewLifecycleOwner){banners->
            val adapter = BannerAdapter(this,banners,this)
            viewPager.adapter = adapter
        }

    }

    override fun onBannerClickListener(banner: Banner) {
        Toast.makeText(requireContext(),banner.title,Toast.LENGTH_LONG).show()
    }
}