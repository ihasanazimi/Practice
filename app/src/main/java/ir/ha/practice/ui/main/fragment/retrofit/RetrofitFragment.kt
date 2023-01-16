package ir.ha.practice.ui.main.fragment.retrofit

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentSampleRequestBinding
import ir.ha.practice.model.Developers
import ir.ha.practice.services.http.ApiService
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitFragment : BaseFragment<FragmentSampleRequestBinding>() {
    override val layoutId: Int get() = R.layout.fragment_sample_request

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pb.visibility = View.INVISIBLE

        binding.requestBtn.setOnClickListener{
            binding.pb.visibility = View.VISIBLE
            ApiService().api.getDevelopersByRetrofit().enqueue(object : Callback<Developers>{
                override fun onResponse(call: Call<Developers>, response: Response<Developers>) {
                    if (response.isSuccessful) { /* code */ }
                }
                override fun onFailure(call: Call<Developers>, t: Throwable) {
                    showToast(requireContext(),t.message.toString())
                    binding.pb.visibility = View.INVISIBLE
                }
            })
        }
    }
}