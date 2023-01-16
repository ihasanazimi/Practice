package ir.ha.practice.services.fresco

import ir.ha.practice.utility.customViews.FrescoImageView

interface ImageLoadingService {
    fun load(imageView : FrescoImageView, imageUrl : String)
}