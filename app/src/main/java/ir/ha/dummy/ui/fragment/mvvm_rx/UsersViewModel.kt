package ir.ha.dummy.ui.fragment.mvvm_rx

import io.reactivex.Single
import ir.ha.dummy.model.UserModel
import ir.ha.dummy.ui.fragment.httpSamples.RetrofitApiService

class UsersViewModel {
    private var apiService : RetrofitApiService = RetrofitApiService()

    fun getNames() : Single<List<UserModel>>{
        return apiService.apis.getPosts()
    }
}