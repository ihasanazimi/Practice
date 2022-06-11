package ir.ha.dep.repo

import ir.ha.dep.model.Banner

object BannerDataGenerator {
    private val banners = arrayListOf<Banner>()
    fun getBanners() : List<Banner> {
        banners.clear()
        for (i in 0..20){
            banners.add(Banner("https://media.wired.com/photos/5ec6fb698971d7886fd36024/125:94/w_1749,h_1315,c_limit/astronaut-urine-elena-lacey-wired-science.jpg","this is tittle"))
        }
        return banners
    }
}