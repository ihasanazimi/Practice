package ir.ha.dummy.ui.fragment.viewPager

import android.os.Bundle
import android.view.View
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentViewPagerSampleBinding
import ir.ha.dummy.model.Banner
import ir.ha.dummy.services.ImageLoadingService
import ir.ha.dummy.utility.base.BaseFragment
import org.koin.android.ext.android.inject

class ViewPagerItem : BaseFragment<FragmentViewPagerSampleBinding>() {

    override val layoutId: Int get() = R.layout.fragment_view_pager_sample

    // object from application class (by koin)
    private val imageLoadingService : ImageLoadingService by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    companion object{
        fun newInstance(banner: Banner) : ViewPagerItem {
            return ViewPagerItem().apply {
                arguments = Bundle().apply {
                    putParcelable("data" ,banner)
                }
            }
        }
    }
}