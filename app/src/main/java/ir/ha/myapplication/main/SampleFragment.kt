package ir.ha.myapplication.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.myapplication.BaseFragment
import ir.ha.myapplication.R
import ir.ha.myapplication.model.adapters.BannerAdapter
import kotlinx.android.synthetic.main.fragment_sample.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SampleFragment : BaseFragment() {

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
            val adapter = BannerAdapter(this,banners)
            viewPager.adapter = adapter
        }



    }
}