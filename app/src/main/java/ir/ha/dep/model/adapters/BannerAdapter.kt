package ir.ha.dep.model.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.ha.dep.feacher.main.fragment.BannerFragment
import ir.ha.dep.model.Banner

class BannerAdapter(val fragment:Fragment, val banners : List<Banner> , val calback : BannersEventListener) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return banners.size
    }


    override fun createFragment(position: Int): Fragment {
        return BannerFragment.newInstance(banners[position])
    }


    fun getFragmentByPosition(position: Int): Fragment {
        return createFragment(position)
    }


    interface BannersEventListener{
        fun onBannerClickListener(banner : Banner)
    }

}