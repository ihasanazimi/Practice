package ir.ha.practice.ui.tabs.components_tab.fragment_types

import android.os.Bundle
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
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.base.BaseFragmentByVM
import ir.ha.practice.utility.extentions.addFragmentByAnimation
import ir.ha.practice.utility.extentions.singleClick
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentsContainer : BaseFragment<FragmentFragmentsContainerBinding>() {
    override val layoutId: Int get() = R.layout.fragment_fragments_container
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun clickEvents() {
        super.clickEvents()

        binding.dialogFragmentBtn.singleClick {
            BaseDialogFragment().show(requireActivity().supportFragmentManager,BaseDialogFragment::class.java.simpleName)
        }

        binding.bottomSheetBtn.singleClick {
            BaseBottomSheetDialog().show(requireActivity().supportFragmentManager,BaseBottomSheetDialog::class.java.simpleName)
        }

        binding.fragment1Btn.singleClick {
            addFragmentByAnimation(BaseFragment1(),BaseFragment1::class.java.simpleName,false,false,R.id.container1)
        }

        binding.fragment2Btn.singleClick {
            addFragmentByAnimation(BaseFragment2(),BaseFragment2::class.java.simpleName,false,false,R.id.container2)
        }

        binding.shareDataToF1.singleClick {
           lifecycleScope.launch {
               repeatOnLifecycle(Lifecycle.State.CREATED){
                   viewModel.dataFragment1.emit(BaseFragment1.data)
               }
           }
        }

        binding.shareDataToF2.singleClick {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED){
                    viewModel.dataFragment2.emit(BaseFragment2.data)
                }
            }
        }
    }
}