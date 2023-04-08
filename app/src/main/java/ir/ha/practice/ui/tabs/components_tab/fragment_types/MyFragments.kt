package ir.ha.practice.ui.tabs.components_tab.fragment_types

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentCreateFragmentBinding
import ir.ha.practice.model.FakePojo
import ir.ha.practice.utility.base.BaseFragment

class MyFragments : BaseFragment<FragmentCreateFragmentBinding>(), View.OnClickListener {
    override val layoutId: Int get() = R.layout.fragment_create_fragment
    private val dataConstant = "data"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogFragment.setOnClickListener(this)
        binding.bottomSheetDialogFragment.setOnClickListener(this)

        val data = requireArguments().getParcelable<FakePojo>(dataConstant)
        Toast.makeText(requireContext(), data?.imageUrl.toString(), Toast.LENGTH_SHORT).show()
        Log.e("imageUrl", "${data?.imageUrl}" )

    }


    fun newInstance(fakeModel :FakePojo): MyFragments {
        val fragment = MyFragments()
        val args = Bundle()
        args.putParcelable(dataConstant,fakeModel)
        fragment.arguments = args
        return fragment
    }

    override fun onClick(p0: View?) {

        when(p0?.id){
            R.id.dialogFragment -> {
                MyDialogFragment().show(requireActivity().supportFragmentManager,"DialogFragmentSample")
            }

            R.id.bottomSheetDialogFragment -> {
                MyBottomSheetDialogFragment().show(requireActivity().supportFragmentManager,"BottomSheetDialogFrg")
            }
        }

    }


}