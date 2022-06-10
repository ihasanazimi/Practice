package ir.ha.dep.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentSampleOfNewCreateFrgBinding
import ir.ha.dep.model.FakeDataModel
import ir.ha.dep.ui.BaseFragment

class SampleOfFragment : BaseFragment(), View.OnClickListener {

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


    fun newInstance(fakeModel :FakeDataModel): SampleOfFragment {
        val fragment = SampleOfFragment()
        val args = Bundle()
        args.putParcelable(dataConstant,fakeModel)
        fragment.arguments = args
        return fragment
    }

    override fun onClick(p0: View?) {

        when(p0?.id){
            R.id.dialogFragment -> {
                DialogFragmentSample().show(requireActivity().supportFragmentManager,"DialogFragmentSample")
            }

            R.id.bottomSheetDialogFragment -> {
                DialogBottomSheetFrg().show(requireActivity().supportFragmentManager,"BottomSheetDialogFrg")
            }
        }

    }


}