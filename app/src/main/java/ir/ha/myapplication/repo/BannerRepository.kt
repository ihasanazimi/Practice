package ir.ha.myapplication.repo

import io.reactivex.Single
import ir.ha.myapplication.model.Banner
import ir.ha.myapplication.model.getFakeDataForSlider

interface BannerRepository {

    fun getAllBanner() : List<Banner>
}