package ir.ha.dep.repo

import ir.ha.dep.model.Banner

object BannerDataGenerator {
    private val banners = arrayListOf<Banner>()
    fun getBanners() : List<Banner> {
        banners.clear()
        for (i in 0..20){
            banners.add(Banner("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Android_logo_2019.png/800px-Android_logo_2019.png","this is tittle"))
        }
        return banners
    }
}