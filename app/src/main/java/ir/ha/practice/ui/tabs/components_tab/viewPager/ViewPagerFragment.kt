package ir.ha.practice.ui.tabs.components_tab.viewPager

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentViewPagerBinding
import ir.ha.practice.repo.BannerDataGenerator
import ir.ha.practice.utility.base.BaseFragment

class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>() {
    override val layoutId: Int get() = R.layout.fragment_view_pager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = ViewPagerAdapter(this@ViewPagerFragment, BannerDataGenerator.getBanners())
        }

    }
}