package ir.ha.practice.ui.tabs.components_tab.loadimage

import android.net.Uri
import android.os.Bundle
import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentLoadImagesBinding
import ir.ha.practice.utility.base.BaseFragment

class LoadImagesFragment : BaseFragment<FragmentLoadImagesBinding>() {
    override val layoutId: Int get() = R.layout.fragment_load_images

    private val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Android_logo_2019.png/800px-Android_logo_2019.png"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** GLIDE - DOC -> https://bumptech.github.io/glide/doc/getting-started.html */
        Glide.with(requireContext()).load(imageUrl)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(binding.glideImageview)


        /** PICASSO - DOC -> https://square.github.io/picasso/ */
        Picasso.get().load(imageUrl)
            .resize(50,50)
            .error(R.drawable.ic_baseline_error_24)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(binding.picassoImagesView)


        /** Coil - DOC -> https://coil-kt.github.io/coil/ */
        binding.coilImages.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_image_24)
            error(R.drawable.ic_baseline_error_24)
            transformations(CircleCropTransformation()) // circle image
        }


        /** Fresco By Custom View : */
        /* Doc -> https://frescolib.org/docs/
            - first initialize fresco in application class
            - tip : place holder set im layout.xml  * */
        binding.frescoImageView.setImageURI(Uri.parse(imageUrl))

    }
}