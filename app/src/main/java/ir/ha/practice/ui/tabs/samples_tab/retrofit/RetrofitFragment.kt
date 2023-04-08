package ir.ha.practice.ui.tabs.samples_tab.retrofit

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentHttpRequestBinding
import ir.ha.practice.model.DeveloperDetails
import ir.ha.practice.services.http.ApiService
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitFragment : BaseFragment<FragmentHttpRequestBinding>() {
    override val layoutId: Int get() = R.layout.fragment_http_request

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pb.visibility = View.INVISIBLE

        binding.requestBtn.setOnClickListener{
            binding.pb.visibility = View.VISIBLE
            ApiService().api.getDevelopersByRetrofit().enqueue(object : Callback<DeveloperDetails>{
                override fun onResponse(call: Call<DeveloperDetails>, response: Response<DeveloperDetails>) {
                    if (response.isSuccessful) { /* code */ }
                }
                override fun onFailure(call: Call<DeveloperDetails>, t: Throwable) {
                    showToast(requireContext(),t.message.toString())
                    binding.pb.visibility = View.INVISIBLE
                }
            })
        }
    }
}