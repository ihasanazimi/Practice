package ir.ha.practice.ui.components.fragment_types

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import ir.ha.practice.R
import ir.ha.practice.databinding.Fragment1Binding
import ir.ha.practice.ui.tabs.components.fragment_types.SharedViewModel
import ir.ha.practice.utility.base.BaseFragment
import kotlinx.coroutines.launch

class FragmentSample1 : BaseFragment<Fragment1Binding>() {
    override val layoutId: Int get() = R.layout.fragment_1
    private val viewModel: SharedViewModel by viewModels()

    val TAG = FragmentSample1::class.java.simpleName

    companion object{
        const val data = "this is data from fragment_1"
    }

    fun newInstance(message : String): FragmentSample1 {
        val args = Bundle()
        val fragment = FragmentSample1()
        fragment.arguments = args
        return fragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ")
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.dataFragment1.collect{
                    binding.messageTv.text = it
                }
            }
        }
    }

}