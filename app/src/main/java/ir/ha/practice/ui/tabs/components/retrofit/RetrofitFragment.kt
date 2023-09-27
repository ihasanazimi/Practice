package ir.ha.practice.ui.tabs.components.retrofit

import android.os.Bundle
import android.view.View
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentHttpRequestBinding
import ir.ha.practice.model.DeveloperDetails
import ir.ha.practice.services.http.ApiService
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.invisible
import ir.ha.practice.utility.extentions.show
import ir.ha.practice.utility.extentions.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitFragment : BaseFragment<FragmentHttpRequestBinding>() {
    override val layoutId: Int get() = R.layout.fragment_http_request

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pb.invisible()
        binding.requestBtn.setOnClickListener{
            binding.pb.show()
            ApiService().api.getDevelopersByRetrofit().enqueue(object : Callback<DeveloperDetails>{
                override fun onResponse(call: Call<DeveloperDetails>, response: Response<DeveloperDetails>) {
                    if (response.isSuccessful) {
                        showToast(requireContext(),response.body()?.firstName.toString())
                        binding.pb.invisible()
                    }
                }
                override fun onFailure(call: Call<DeveloperDetails>, t: Throwable) {
                    showToast(requireContext(),t.message.toString())
                    binding.pb.invisible()
                }
            })
        }
    }
}