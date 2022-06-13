package ir.ha.dep.ui.fragment.material

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentMaterialViewsBinding
import ir.ha.dep.ui.BaseFragment

class MaterialViews : BaseFragment() {

    private lateinit var binding : FragmentMaterialViewsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = getBinding(R.layout.fragment_material_views,container!!)
        return binding.root
    }


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