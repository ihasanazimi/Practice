package ir.ha.practice.ui.tabs.samples_tab.viewPager

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentViewPagerBinding
import ir.ha.practice.model.BannerSlider
import ir.ha.practice.services.fresco.ImageLoadingService
import ir.ha.practice.utility.base.BaseFragment
import org.koin.android.ext.android.inject

class ViewPagerItem : BaseFragment<FragmentViewPagerBinding>() {
    override val layoutId: Int get() = R.layout.fragment_view_pager

    // inject a object from application class (by KOIN)
    private val imageLoadingService : ImageLoadingService by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object{
        fun newInstance(bannerSlider: BannerSlider) : ViewPagerItem {
            return ViewPagerItem().apply {
                arguments = Bundle().apply { putParcelable("data" ,bannerSlider) }
            }
        }
    }
}