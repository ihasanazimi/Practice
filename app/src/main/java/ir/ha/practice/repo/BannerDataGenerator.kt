package ir.ha.practice.repo

import ir.ha.practice.model.BannerSlider

object BannerDataGenerator {
    private val bannerSliders = arrayListOf<BannerSlider>()
    fun getBanners() : List<BannerSlider> {
        bannerSliders.clear()
        for (i in 0..20) bannerSliders.add(BannerSlider("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Android_logo_2019.png/800px-Android_logo_2019.png","this is tittle"))
        return bannerSliders
    }
}