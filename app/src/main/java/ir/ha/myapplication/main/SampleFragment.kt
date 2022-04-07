package ir.ha.myapplication.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import ir.ha.myapplication.BaseFragment
import ir.ha.myapplication.R
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel

class SampleFragment : BaseFragment() {

    val viewModel : SampleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_sample,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProgressIndicator(true)
        lifecycleScope.launchWhenCreated {
            viewModel.getAllArticle().observe(viewLifecycleOwner) {
                Log.i("hsn", "onViewCreated: " + it.size)
                setProgressIndicator(false)
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Log.i("hsnError", "onViewCreated: " + it)
            setProgressIndicator(false)
        }



    }
}