package ir.ha.dep.repo

import ir.ha.dep.repo.dataSource.BannerLocalDataSource
import ir.ha.dep.model.Banner

class BannerRepositoryImpl(val localDataSource : BannerLocalDataSource) : BannerRepository {

    override fun getAllBanner(): List<Banner> {
        return localDataSource.getBanners()
    }

}
