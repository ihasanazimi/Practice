package ir.ha.practice.ui.main.fragment.mvvm_rx

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import ir.ha.practice.model.Developers
import ir.ha.practice.services.http.ApiService

class UsersViewModel : ViewModel() {
    fun getDevelopers() : Observable<Developers> = ApiService().api.getDevelopersByRx()
}