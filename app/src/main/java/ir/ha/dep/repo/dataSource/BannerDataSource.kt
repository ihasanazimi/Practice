package ir.ha.dep.repo.dataSource

import ir.ha.dep.model.Banner

interface BannerDataSource {
    fun getBanners() : List<Banner>
}