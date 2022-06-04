package ir.ha.dep.repo.dataSource

import ir.ha.dep.model.Banner
import ir.ha.dep.model.getFakeDataForSlider

class BannerLocalDataSource() : BannerDataSource {

    override fun getBanners(): List<Banner> = getFakeDataForSlider()
}