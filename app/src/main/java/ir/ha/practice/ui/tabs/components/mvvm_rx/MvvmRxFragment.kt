package ir.ha.practice.ui.tabs.components.mvvm_rx

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentMvvmRxBinding
import ir.ha.practice.model.DeveloperDetails
import ir.ha.practice.utility.base.BaseFragmentByVM

class MvvmRxFragment : BaseFragmentByVM<FragmentMvvmRxBinding, UsersVM>() {
    override val layoutId: Int get() = R.layout.fragment_mvvm_rx
    override val viewModel: UsersVM get() = ViewModelProvider(this)[UsersVM::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pb.visibility = View.VISIBLE


        viewModel.getDevelopersSingleObservable()
            .doFinally{ requireActivity().runOnUiThread{ binding.pb.visibility = View.GONE } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<DeveloperDetails>{
                override fun onSubscribe(d: Disposable) {viewModel.compositeDisposable}
                override fun onSuccess(t: DeveloperDetails) {}
                override fun onError(e: Throwable) {}
            })

        viewModel.getDevelopers()
            .doFinally{ requireActivity().runOnUiThread{ binding.pb.visibility = View.GONE } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DeveloperDetails>{
                override fun onSubscribe(d: Disposable) {viewModel.compositeDisposable}
                override fun onNext(t: DeveloperDetails) {}
                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            })
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}