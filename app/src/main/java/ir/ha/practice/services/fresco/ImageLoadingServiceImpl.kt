package ir.ha.practice.services.fresco

import com.facebook.drawee.view.SimpleDraweeView
import ir.ha.practice.utility.customViews.FrescoImageView
import java.lang.IllegalStateException

class ImageLoadingServiceImpl : ImageLoadingService {

    override fun load(imageView: FrescoImageView, url: String) {
        try {
            if (imageView is SimpleDraweeView){
                imageView.setImageURI(url)
            }
        }catch (e : IllegalStateException){
            throw IllegalAccessException("image view must be instances of fresco")
        }
    }
}