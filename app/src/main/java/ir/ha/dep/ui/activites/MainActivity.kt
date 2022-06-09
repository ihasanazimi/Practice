package ir.ha.dep.ui.activites

import android.os.Bundle
import android.view.View
import ir.ha.dep.R
import ir.ha.dep.databinding.ActivityMainBinding
import ir.ha.dep.feacher.BaseActivity
import ir.ha.dep.ui.fragment.bannerFrg.BannerSliderSampleFrg
import ir.ha.dep.ui.fragment.LoadImagesFrg

class MainActivity : BaseActivity(), View.OnClickListener {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_main)
        setContentView(binding.root)
        binding.imageLoaderBtn.setOnClickListener(this)
        binding.bannerSliderBtn.setOnClickListener(this)
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
                    .add(R.id.mainFrame, BannerSliderSampleFrg(),"BannerSliderTag")
                    .addToBackStack("BannerSliderTag")
                    .commit()
            }

            else -> {}
        }
    }
}