package ir.ha.dep.repo.dataSource

import ir.ha.dep.model.Banner
import ir.ha.dep.model.FakeDataGenerator

class BannerLocalDataSource() : BannerDataSource {

    override fun getBanners(): List<Banner> = getBanners()
}