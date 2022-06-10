package ir.ha.dep.ui.activites

import android.os.Bundle
import android.view.View
import ir.ha.dep.R
import ir.ha.dep.databinding.ActivityMainBinding
import ir.ha.dep.ui.BaseActivity
import ir.ha.dep.model.FakeDataModel
import ir.ha.dep.ui.fragment.viewPager.ViewPagerSampleFrg
import ir.ha.dep.ui.fragment.LoadImagesFrg
import ir.ha.dep.ui.fragment.RecyclerViewSampleFrg
import ir.ha.dep.ui.fragment.SampleOfFragment

class MainActivity : BaseActivity(), View.OnClickListener {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_main)
        setContentView(binding.root)
        binding.imageLoaderBtn.setOnClickListener(this)
        binding.bannerSliderBtn.setOnClickListener(this)
        binding.recyclerViewSampleBtn.setOnClickListener(this)
        binding.FragmentSample.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imageLoaderBtn -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainFrame,LoadImagesFrg(),"loadImagesTag")
                    .addToBackStack("loadImagesTag")
                    .commit()
            }


            R.id.bannerSliderBtn ->{
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainFrame, ViewPagerSampleFrg(),"BannerSliderTag")
                    .addToBackStack("BannerSliderTag")
                    .commit()
            }


            R.id.recyclerViewSampleBtn -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainFrame,RecyclerViewSampleFrg(),"RecyclerViewSampleFrg")
                    .addToBackStack("RecyclerViewSampleFrg")
                    .commit()
            }


            R.id.FragmentSample -> {

                /** how to pass data model into fragment by newInstance method...  */
                val fakeDataModel = FakeDataModel("Hsn", "https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/326012_1100-800x825.jpg")
                val frg = SampleOfFragment().newInstance(fakeDataModel)
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainFrame, frg, "SampleOfFragment")
                    .addToBackStack("SampleOfFragment")
                    .commit()
            }

            else -> {}
        }
    }
}