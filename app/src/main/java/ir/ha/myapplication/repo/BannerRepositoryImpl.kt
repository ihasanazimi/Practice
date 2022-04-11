package ir.ha.myapplication.repo

import ir.ha.myapplication.repo.datasource.BannerLocalDataSource
import ir.ha.myapplication.model.Banner

class BannerRepositoryImpl(val localDataSource : BannerLocalDataSource) : BannerRepository {

    override fun getAllBanner(): List<Banner> {
        return localDataSource.getBanners()
    }

}
