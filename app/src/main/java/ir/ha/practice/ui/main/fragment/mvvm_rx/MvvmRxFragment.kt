package ir.ha.practice.ui.main.fragment.mvvm_rx

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentMvvmRxJavaBinding
import ir.ha.practice.model.Developers
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.base.BaseFragmentByVM
import ir.ha.practice.utility.extentions.showToast

class MvvmRxFragment : BaseFragmentByVM<FragmentMvvmRxJavaBinding,UsersVM>() {
    override val layoutId: Int get() = R.layout.fragment_mvvm_rx_java
    override val viewModel: UsersVM get() = ViewModelProvider(this)[UsersVM::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pb.visibility = View.VISIBLE


        viewModel.getDevelopersSingleObservable()
            .doFinally{ requireActivity().runOnUiThread{ binding.pb.visibility = View.GONE } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Developers>{
                override fun onSubscribe(d: Disposable) {viewModel.compositeDisposable}
                override fun onSuccess(t: Developers) {}
                override fun onError(e: Throwable) {}
            })

        viewModel.getDevelopers()
            .doFinally{ requireActivity().runOnUiThread{ binding.pb.visibility = View.GONE } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Developers>{
                override fun onSubscribe(d: Disposable) {viewModel.compositeDisposable}
                override fun onNext(t: Developers) {}
                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            })
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}