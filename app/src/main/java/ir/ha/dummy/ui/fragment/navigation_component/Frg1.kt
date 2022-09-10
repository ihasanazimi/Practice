package ir.ha.dummy.ui.fragment.navigation_component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ir.ha.dummy.R
import ir.ha.dummy.databinding.Fragment1Binding
import ir.ha.dummy.ui.BaseFragment

class Frg1 : BaseFragment() {

    private lateinit var binding : Fragment1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(R.layout.fragment_1,container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_frg1_to_frg2)
        }
    }
}