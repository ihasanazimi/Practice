package ir.ha.practice.ui.components.mvvm_rx

import io.reactivex.Observable
import io.reactivex.Single
import ir.ha.practice.model.data.DeveloperDetailsRemoteResponse
import ir.ha.practice.services.http.ApiService
import ir.ha.practice.utility.base.BaseViewModel

class MvvmRxJavaFragmentVM : BaseViewModel() {
    fun getDevelopers() : Observable<DeveloperDetailsRemoteResponse> = ApiService().api.getDevelopersByRx()
    fun getDevelopersSingleObservable() : Single<DeveloperDetailsRemoteResponse> = ApiService().api.getDevelopersBySingleObservable()
}