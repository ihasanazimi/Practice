package ir.ha.dep.model.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.ha.dep.ui.fragment.bannerFrg.BannerItemFrg
import ir.ha.dep.model.Banner

class BannerAdapter(val fragment:Fragment, val banners : List<Banner>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return banners.size
    }


    override fun createFragment(position: Int): Fragment {
        return BannerItemFrg.newInstance(banners[position])
    }


    fun getFragmentByPosition(position: Int): Fragment {
        return createFragment(position)
    }


    interface BannersEventListener{
        fun onBannerClickListener(banner : Banner)
    }

}