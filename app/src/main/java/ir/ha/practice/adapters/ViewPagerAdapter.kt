package ir.ha.practice.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.ha.practice.model.entities.BannerSliderEntity
import ir.ha.practice.ui.components.viewPager.ViewPagerItemFragment

class ViewPagerAdapter(
    val fragment:Fragment,
    private val bannerSliderEntities : List<BannerSliderEntity>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount()= bannerSliderEntities.size

    override fun createFragment(position: Int): Fragment {
        return ViewPagerItemFragment.newInstance(bannerSliderEntities[position])
    }
}