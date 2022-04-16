package ir.ha.myapplication.feacher.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ir.ha.myapplication.feacher.BaseFragment
import ir.ha.myapplication.R
import ir.ha.myapplication.model.Banner
import ir.ha.myapplication.model.adapters.BannerAdapter
import kotlinx.android.synthetic.main.fragment_sample.*
import kotlinx.android.synthetic.main.slider_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SampleFragment : BaseFragment(), BannerAdapter.BannersEventListener {

    val viewModel by viewModel<SampleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_sample,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setProgressIndicator(true)
//        lifecycleScope.launchWhenCreated {
//            viewModel.getAllArticle().observe(viewLifecycleOwner) {
//                Log.i("hsn", "onViewCreated: " + it.size)
//                setProgressIndicator(false)
//            }
//        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Log.i("hsnError", "onViewCreated: " + it)
        }


        viewModel.getBanners().observe(viewLifecycleOwner){banners->
            val adapter = BannerAdapter(this,banners,this)
            viewPager.adapter = adapter
//            adapter.getFragmentByPosition(2).item.setOnClickListener{
//                Toast.makeText(requireContext(),"test",Toast.LENGTH_LONG).show()
//            }
        }



    }

    override fun onBannerClickListener(banner: Banner) {
        Toast.makeText(requireContext(),banner.title,Toast.LENGTH_LONG).show()
    }
}