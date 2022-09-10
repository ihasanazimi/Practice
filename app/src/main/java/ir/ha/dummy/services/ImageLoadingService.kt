package ir.ha.dummy.services

import ir.ha.dummy.utility.customViews.FrescoImageView

interface ImageLoadingService {

    fun load(imageView : FrescoImageView, imageUrl : String)
}