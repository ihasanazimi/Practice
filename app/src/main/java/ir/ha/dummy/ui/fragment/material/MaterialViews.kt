package ir.ha.dummy.ui.fragment.material

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentMaterialViewsBinding
import ir.ha.dummy.utility.base.BaseFragment

class MaterialViews : BaseFragment<FragmentMaterialViewsBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_material_views

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** material card view */
        binding.materialCardView.setOnClickListener{
            // how to check material card view selected mode (checked) ?
            binding.materialCardView.apply {
                isCheckable = true
                isChecked = !this.isChecked
            }
        }


        /** SnackBar Sample */
        Snackbar.make(binding.root , "this is test msg.." , Snackbar.ANIMATION_MODE_SLIDE).show()
    }
}