package ir.ha.dummy.ui.fragment.mvvm_rx

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import ir.ha.dummy.model.UserModel
import ir.ha.dummy.ui.fragment.httpSamples.RetrofitApiService
import ir.ha.dummy.utility.base.BaseViewModel

class UsersViewModel : ViewModel() {
    private var apiService : RetrofitApiService = RetrofitApiService()

    fun getNames() : Single<List<UserModel>>{
        return apiService.apis.getPosts()
    }
}