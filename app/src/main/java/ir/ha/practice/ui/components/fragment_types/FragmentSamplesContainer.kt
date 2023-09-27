package ir.ha.practice.ui.components.fragment_types

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentFragmentsContainerBinding
import ir.ha.practice.ui.components.fragment_types.bottom_sheet.BottomSheetSample
import ir.ha.practice.ui.components.fragment_types.dialog.DialogSample
import ir.ha.practice.ui.tabs.components.fragment_types.SharedViewModel
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.addFragmentByAnimation
import ir.ha.practice.utility.extentions.singleClick
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentSamplesContainer : BaseFragment<FragmentFragmentsContainerBinding>() {
    override val layoutId: Int get() = R.layout.fragment_fragments_container
    private val viewModel: SharedViewModel by viewModels()

    val TAG = FragmentSamplesContainer::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        Log.i(TAG, "onCreateView: ")
    }

    override fun registerClickListeners() {
        super.registerClickListeners()

        binding.dialogFragmentBtn.singleClick {
            DialogSample().show(requireActivity().supportFragmentManager, DialogSample::class.java.simpleName)
        }

        binding.bottomSheetBtn.singleClick {
            BottomSheetSample().show(requireActivity().supportFragmentManager, BottomSheetSample::class.java.simpleName)
        }

        binding.fragment1Btn.singleClick {
            addFragmentByAnimation(
                FragmentSample1(),
                FragmentSample1::class.java.simpleName,false,false,R.id.container1)
        }

        binding.fragment2Btn.singleClick {
            addFragmentByAnimation(
                FragmentSample2(),
                FragmentSample2::class.java.simpleName,false,false,R.id.container2)
        }

        binding.shareDataToF1.singleClick {
           lifecycleScope.launch {
               repeatOnLifecycle(Lifecycle.State.CREATED){
                   viewModel.dataFragment1.emit(FragmentSample1.data)
               }
           }
        }

        binding.shareDataToF2.singleClick {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED){
                    viewModel.dataFragment2.emit(FragmentSample2.DATA)
                }
            }
        }
    }
}