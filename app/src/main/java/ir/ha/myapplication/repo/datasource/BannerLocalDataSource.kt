package ir.ha.myapplication.repo.datasource

import ir.ha.myapplication.model.Banner
import ir.ha.myapplication.model.getFakeDataForSlider

class BannerLocalDataSource() : BannerDataSource {

    override fun getBanners(): List<Banner> = getFakeDataForSlider()
}