package ir.ha.practice.ui.tabs.components_tab.viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.ha.practice.model.BannerSlider

class ViewPagerAdapter(val fragment:Fragment, private val bannerSliders : List<BannerSlider>) : FragmentStateAdapter(fragment) {

    override fun getItemCount()= bannerSliders.size

    override fun createFragment(position: Int): Fragment {
        return ViewPagerItem.newInstance(bannerSliders[position])
    }
}