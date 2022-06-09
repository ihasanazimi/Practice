package ir.ha.dep.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import ir.ha.dep.R
import ir.ha.dep.databinding.LoadImagesLayoutBinding
import ir.ha.dep.feacher.BaseFragment

class LoadImagesFragment : BaseFragment() {

    lateinit var binding : LoadImagesLayoutBinding
    val imageUrl = "https://media.wired.com/photos/5ec6fb698971d7886fd36024/125:94/w_1749,h_1315,c_limit/astronaut-urine-elena-lacey-wired-science.jpg"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.load_images_layout, container!!)
        return binding.root
    }

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
            transformations(CircleCropTransformation()) // circle image
        }


        /** Fresco By Custom View*/
        /**
         * first initialize fresco in application class
         * tip : place holder set im layout.xml
         * */
        binding.frescoImageView.setImageURI(Uri.parse(imageUrl))


    }
}