package ir.ha.dummy.ui.fragment.viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.ha.dummy.model.Banner

class ViewPagerAdapter(val fragment:Fragment, private val banners : List<Banner>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return banners.size
    }

    override fun createFragment(position: Int): Fragment {
        return ViewPagerItem.newInstance(banners[position])
    }

}