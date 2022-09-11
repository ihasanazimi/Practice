package ir.ha.dummy.ui.fragment.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.R
import ir.ha.dummy.model.Banner
import ir.ha.dummy.services.ImageLoadingService
import ir.ha.dummy.utility.customViews.FrescoImageView
import org.koin.android.ext.android.inject

class ViewPagerItem : BaseFragment() {

    // object from application class (by koin)
    private val imageLoadingService : ImageLoadingService by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        // inflate item
        val item = inflater.inflate(R.layout.item_view_pager,container,false) as FrescoImageView

        // (load) fresco custom function
        imageLoadingService.load(item,requireArguments().getParcelable<Banner>("data")!!.ImageUrl)

        // return item view
        return item
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