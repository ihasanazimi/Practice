package ir.ha.myapplication.services

import ir.ha.myapplication.view.FrescoImageView

interface ImageLoadingService {

    fun load(imageView : FrescoImageView, imageUrl : String)
}