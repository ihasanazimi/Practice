package ir.ha.practice.ui.main.fragment.mvvm_rx

import android.os.Bundle
import android.view.View
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
import ir.ha.practice.model.UserModel
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast

class MvvmRxFragment : BaseFragment<FragmentMvvmRxJavaBinding>() {
    override val layoutId: Int get() = R.layout.fragment_mvvm_rx_java

    private val viewModel: UsersViewModel get() = UsersViewModel()
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pb.visibility = View.VISIBLE


        viewModel.getDevelopers()
            .doFinally{ requireActivity().runOnUiThread{ binding.pb.visibility = View.GONE } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Developers>{
                override fun onSubscribe(d: Disposable) { compositeDisposable.add(d) }
                override fun onNext(t: Developers) { showToast(requireContext(), t[0].firstName) }
                override fun onError(e: Throwable) { showToast(requireContext(),e.message.toString()) }
                override fun onComplete() { showToast(requireContext(),"Done Successfully") }
            })
    }


    override fun onDestroy() {
        super.onDestroy()
        if (compositeDisposable!= null) compositeDisposable.clear()
    }

}