package ir.ha.myapplication.repo.datasource

import ir.ha.myapplication.model.Banner

interface BannerDataSource {
    fun getBanners() : List<Banner>
}