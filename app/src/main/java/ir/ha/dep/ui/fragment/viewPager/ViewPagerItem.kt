package ir.ha.dep.ui.fragment.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.R
import ir.ha.dep.model.Banner
import ir.ha.dep.services.ImageLoadingService
import ir.ha.dep.utility.customViews.FrescoImageView
import org.koin.android.ext.android.inject

class ViewPagerItem : BaseFragment() {

    // object from application class (by koin)
    private val imageLoadingService : ImageLoadingService by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val item = inflater.inflate(R.layout.item_view_pager,container,false) as FrescoImageView
        val banner = requireArguments().getParcelable<Banner>("data")
        imageLoadingService.load(item,banner!!.ImageUrl) // fresco
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