package ir.ha.dummy.ui.fragment.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentViewPagerSampleBinding
import ir.ha.dummy.repo.BannerDataGenerator
import ir.ha.dummy.utility.base.BaseFragment

class ViewPagerSampleFrg : BaseFragment() {

    private lateinit var binding : FragmentViewPagerSampleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = getBinding(R.layout.fragment_view_pager_sample, container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = ViewPagerAdapter(this@ViewPagerSampleFrg , BannerDataGenerator.getBanners())
        }

    }
}