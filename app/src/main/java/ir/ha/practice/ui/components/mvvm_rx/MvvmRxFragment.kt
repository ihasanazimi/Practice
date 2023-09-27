package ir.ha.practice.ui.components.mvvm_rx

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
import ir.ha.practice.model.data.DeveloperDetailsRemoteResponse
import ir.ha.practice.utility.base.BaseFragmentByVM

class MvvmRxFragment : BaseFragmentByVM<FragmentMvvmRxBinding, MvvmRxJavaFragmentVM>() {
    override val layoutId: Int get() = R.layout.fragment_mvvm_rx
    override val viewModel: MvvmRxJavaFragmentVM get() = ViewModelProvider(this)[MvvmRxJavaFragmentVM::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pb.visibility = View.VISIBLE


        viewModel.getDevelopersSingleObservable()
            .doFinally{ requireActivity().runOnUiThread{ binding.pb.visibility = View.GONE } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<DeveloperDetailsRemoteResponse>{
                override fun onSubscribe(d: Disposable) {viewModel.compositeDisposable}
                override fun onSuccess(t: DeveloperDetailsRemoteResponse) {}
                override fun onError(e: Throwable) {}
            })

        viewModel.getDevelopers()
            .doFinally{ requireActivity().runOnUiThread{ binding.pb.visibility = View.GONE } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DeveloperDetailsRemoteResponse>{
                override fun onSubscribe(d: Disposable) {viewModel.compositeDisposable}
                override fun onNext(t: DeveloperDetailsRemoteResponse) {}
                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            })
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}