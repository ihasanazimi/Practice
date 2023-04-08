package ir.ha.practice.ui.tabs.samples_tab.mvvm_rx

import io.reactivex.Observable
import io.reactivex.Single
import ir.ha.practice.model.DeveloperDetails
import ir.ha.practice.services.http.ApiService
import ir.ha.practice.utility.base.BaseViewModel

class UsersVM : BaseViewModel() {
    fun getDevelopers() : Observable<DeveloperDetails> = ApiService().api.getDevelopersByRx()
    fun getDevelopersSingleObservable() : Single<DeveloperDetails> = ApiService().api.getDevelopersBySingleObservable()
}