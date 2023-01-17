package ir.ha.practice.ui.main.fragment.mvvm_rx

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Single
import ir.ha.practice.model.Developers
import ir.ha.practice.services.http.ApiService
import ir.ha.practice.utility.base.BaseViewModel

class UsersVM : BaseViewModel() {
    fun getDevelopers() : Observable<Developers> = ApiService().api.getDevelopersByRx()
    fun getDevelopersSingleObservable() : Single<Developers> = ApiService().api.getDevelopersBySingleObservable()
}