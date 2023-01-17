package ir.ha.practice.ui.main.fragment.material

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentMaterialViewsBinding
import ir.ha.practice.utility.base.BaseFragment

class MaterialDesignFragment : BaseFragment<FragmentMaterialViewsBinding>() {
    override val layoutId: Int get() = R.layout.fragment_material_views

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** SnackBar Sample */
        Snackbar.make(binding.root , "this is test msg.." , Snackbar.ANIMATION_MODE_SLIDE).show()
    }


    override fun clickEvents() {
        super.clickEvents()

        /** material card view */
        binding.materialCardView.setOnClickListener{
            // how to check material card view selected mode (checked) ?
            binding.materialCardView.apply {
                isCheckable = true
                isChecked = !this.isChecked
            }
        }
    }
}