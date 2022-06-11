package ir.ha.dep.ui.activites

import android.os.Bundle
import android.view.View
import ir.ha.dep.R
import ir.ha.dep.databinding.ActivityMainBinding
import ir.ha.dep.ui.BaseActivity
import ir.ha.dep.model.FakeDataModel
import ir.ha.dep.ui.fragment.viewPager.ViewPagerSampleFrg
import ir.ha.dep.ui.fragment.LoadImagesSampleFrg
import ir.ha.dep.ui.fragment.RecyclerViewSampleFrg
import ir.ha.dep.ui.fragment.SampleOfFrg
import ir.ha.dep.utility.extentions.addFragmentByAnimation
import ir.ha.dep.utility.extentions.showToast

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
                addFragmentByAnimation(LoadImagesSampleFrg(),"loadImagesTag",
                    addToBackStack = true,
                    customAnimations = true,
                    containerViewId = R.id.mainFrame,
                    commitAllowingStateLoss = false
                )
            }


            R.id.bannerSliderBtn ->{

                addFragmentByAnimation(ViewPagerSampleFrg(),"BannerSliderTag",
                    addToBackStack = true,
                    customAnimations = true,
                    containerViewId = R.id.mainFrame,
                    commitAllowingStateLoss = false
                )
            }


            R.id.recyclerViewSampleBtn -> {
                addFragmentByAnimation(RecyclerViewSampleFrg(),"RecyclerViewSampleFrg",
                    addToBackStack = true,
                    customAnimations = true,
                    containerViewId = R.id.mainFrame,
                    commitAllowingStateLoss = false
                )
            }


            R.id.FragmentSample -> {

                /** how to pass data model into fragment by newInstance method...  */
                val fakeDataModel = FakeDataModel("Hsn", "https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/326012_1100-800x825.jpg")
                val frg = SampleOfFrg().newInstance(fakeDataModel)
                addFragmentByAnimation(frg,"SampleOfFragment",
                    addToBackStack = true,
                    customAnimations = true,
                    containerViewId = R.id.mainFrame,
                    commitAllowingStateLoss = false
                )
            }

            else -> { showToast("unKnow Error")} }
    }
}