package ir.ha.dep.repo

import ir.ha.dep.model.Banner

interface BannerRepository {

    fun getAllBanner() : List<Banner>
}