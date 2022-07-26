package ir.ha.dep.ui.fragment.mvvm_rx

import io.reactivex.Single
import ir.ha.dep.model.UserModel
import ir.ha.dep.ui.httpSamples.RetrofitApiService

class UsersViewModel {
    private var apiService : RetrofitApiService = RetrofitApiService()

    fun getNames() : Single<List<UserModel>>{
        return apiService.apis.getPosts()
    }
}