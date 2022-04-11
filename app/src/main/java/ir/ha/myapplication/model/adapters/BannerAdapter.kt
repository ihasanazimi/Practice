package ir.ha.myapplication.model.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.ha.myapplication.main.BannerFragment
import ir.ha.myapplication.model.Banner

class BannerAdapter(fragment:Fragment, val banners : List<Banner>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return banners.size
    }


    override fun createFragment(position: Int): Fragment {

        return BannerFragment.newInstance(banners[position])
    }

}