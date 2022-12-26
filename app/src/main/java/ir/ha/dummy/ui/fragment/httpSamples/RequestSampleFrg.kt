package ir.ha.dummy.ui.fragment.httpSamples

import android.os.Bundle
import android.view.View
import ir.ha.dummy.ApplicationLoader
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentSampleRequestBinding
import ir.ha.dummy.model.UserModel
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestSampleFrg : BaseFragment<FragmentSampleRequestBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_sample_request

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pb.visibility = View.INVISIBLE

        binding.requestBtn.setOnClickListener{
            /** sample of request by retrofit */

            binding.pb.visibility = View.VISIBLE
            val apiService = RetrofitApiService()
            apiService.apis.getPostsByRetrofit().enqueue(object : Callback<List<UserModel>>{
                override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                    if (response.isSuccessful) {
                        binding.tv.text = "${response.body()?.get(0)?.title}"
                        showToast(requireContext(),"با موفقیت انجام شد")
                        binding.pb.visibility = View.INVISIBLE
                    }
                }

                override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                    showToast(ApplicationLoader.context!!,t.message.toString())
                    binding.pb.visibility = View.INVISIBLE
                }
            })
        }


        /** Gson Sample */
//        val s = Gson().fromJson<String>(JsonObject())

    }
}