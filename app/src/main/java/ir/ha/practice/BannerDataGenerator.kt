package ir.ha.practice

import ir.ha.practice.data.entities.BannerSliderEntity

object BannerDataGenerator {
    private val bannerSliderEntities = arrayListOf<BannerSliderEntity>()
    fun getBanners() : List<BannerSliderEntity> {
        bannerSliderEntities.clear()
        for (i in 0..20) bannerSliderEntities.add(BannerSliderEntity("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Android_logo_2019.png/800px-Android_logo_2019.png","this is tittle"))
        return bannerSliderEntities
    }
}