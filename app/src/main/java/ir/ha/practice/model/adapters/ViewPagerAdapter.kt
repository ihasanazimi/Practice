package ir.ha.practice.model.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.ha.practice.model.BannerSlider
import ir.ha.practice.ui.tabs.components.viewPager.ViewPagerItem

class ViewPagerAdapter(
    val fragment:Fragment,
    private val bannerSliders : List<BannerSlider>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount()= bannerSliders.size

    override fun createFragment(position: Int): Fragment {
        return ViewPagerItem.newInstance(bannerSliders[position])
    }
}