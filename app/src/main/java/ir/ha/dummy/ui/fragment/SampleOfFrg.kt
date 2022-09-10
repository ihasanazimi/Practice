package ir.ha.dummy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentSampleOfNewCreateFrgBinding
import ir.ha.dummy.model.FakeDataModel
import ir.ha.dummy.ui.BaseFragment

class SampleOfFrg : BaseFragment(), View.OnClickListener {

    lateinit var binding : FragmentSampleOfNewCreateFrgBinding
    private val dataConstant = "data"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = getBinding(R.layout.fragment_sample_of_new_create_frg,container!!)

        binding.dialogFragment.setOnClickListener(this)
        binding.bottomSheetDialogFragment.setOnClickListener(this)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = requireArguments().getParcelable<FakeDataModel>(dataConstant)
        Toast.makeText(requireContext(), data?.imageUrl.toString(), Toast.LENGTH_SHORT).show()
        Log.e("hsn", "${data?.imageUrl}" )

    }


    fun newInstance(fakeModel :FakeDataModel): SampleOfFrg {
        val fragment = SampleOfFrg()
        val args = Bundle()
        args.putParcelable(dataConstant,fakeModel)
        fragment.arguments = args
        return fragment
    }

    override fun onClick(p0: View?) {

        when(p0?.id){
            R.id.dialogFragment -> {
                DialogFrgSample().show(requireActivity().supportFragmentManager,"DialogFragmentSample")
            }

            R.id.bottomSheetDialogFragment -> {
                DialogBottomSheetFrg().show(requireActivity().supportFragmentManager,"BottomSheetDialogFrg")
            }
        }

    }


}