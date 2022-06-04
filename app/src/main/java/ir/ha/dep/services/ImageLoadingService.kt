package ir.ha.dep.services

import ir.ha.dep.utility.customViews.FrescoImageView

interface ImageLoadingService {

    fun load(imageView : FrescoImageView, imageUrl : String)
}