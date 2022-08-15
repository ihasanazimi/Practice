package ir.ha.dep.ui.fragment.httpSamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.dmoral.toasty.Toasty
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentSampleRequestBinding
import ir.ha.dep.model.UserModel
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestSampleFrg : BaseFragment() {

    lateinit var binding  : FragmentSampleRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_sample_request,container!!)
        return binding.root
    }


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
                        Toasty.success(requireContext(), "با موفقیت انجام شد").show()
                        binding.pb.visibility = View.INVISIBLE
                    }
                }

                override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                    showToast(requireContext(),t.message.toString())
                    binding.pb.visibility = View.INVISIBLE
                }
            })
        }


        /** Gson Sample */
//        val s = Gson().fromJson<String>(JsonObject())

    }
}