package ir.ha.dep.ui.activites

import android.os.Bundle
import android.util.Log
import android.view.View
import ir.ha.dep.R
import ir.ha.dep.databinding.ActivityMainBinding
import ir.ha.dep.ui.BaseActivity
import ir.ha.dep.model.FakeDataModel
import ir.ha.dep.ui.fragment.*
import ir.ha.dep.ui.fragment.viewPager.ViewPagerSampleFrg
import ir.ha.dep.ui.fragment.material.MaterialViews
import ir.ha.dep.ui.httpsamples.RequestSampleFrg
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
        binding.materialDesign.setOnClickListener(this)
        binding.lottieAnimationView.setOnClickListener(this)
        binding.multiThreadingBtn.setOnClickListener(this)
        binding.retrofitOkHttpBtn.setOnClickListener(this)
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


            R.id.materialDesign -> {
                addFragmentByAnimation(MaterialViews(),"ToolbarFrgSample",
                    addToBackStack = true,
                    customAnimations = true,
                    containerViewId = R.id.mainFrame,
                    commitAllowingStateLoss = false)
            }

            R.id.lottieAnimationView -> {
                    addFragmentByAnimation(AnimationsFrg(),"AnimationsFrg",
                        addToBackStack = true,
                        customAnimations = true,
                        containerViewId = R.id.mainFrame,
                        commitAllowingStateLoss = false)
            }

            R.id.retrofitOkHttpBtn -> {
                addFragmentByAnimation(RequestSampleFrg(),"RequestFrgSample",
                    addToBackStack = true,
                    customAnimations = true,
                    containerViewId = R.id.mainFrame,
                    commitAllowingStateLoss = false)
            }



            R.id.multiThreadingBtn -> {

                showToast(this,"output in Logcat..")

                // solution 1
                MultiThreadingClass().start() // outPut -> multiThreading current threadName in Log.e / tag = hsn


                // solution 2
                Thread(object  : MyRunnableThread() {
                    override fun run() {
                        // done superClass code block
                        super.run() // outPut -> multiThreading current threadName in Log.e / tag = hsn
                    }
                }).start()


                // solution 3 by lambda
                Thread {
                    Log.e("hsn", "solution 3 by lambda  ->  " + Thread.currentThread().name)
                }.start()

            }

            else -> { showToast(this,"unKnow Error")} }
    }
}