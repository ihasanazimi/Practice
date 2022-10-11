package ir.ha.dummy.ui.fragment.mvvm_rx

import android.os.Bundle
import android.view.View
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentMvvmRxJavaBinding
import ir.ha.dummy.model.UserModel
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.showToast

class MVVMRxJavaFrg : BaseFragment<FragmentMvvmRxJavaBinding>() {
    override val layoutId: Int get() = R.layout.fragment_mvvm_rx_java
    private val viewModel: UsersViewModel get() = UsersViewModel()
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pb.visibility = View.VISIBLE


        viewModel.getNames()
            .doFinally{
               requireActivity().runOnUiThread{
                   binding.pb.visibility = View.GONE
               }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<UserModel>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<UserModel>) {
                    binding.tvMvvmRxMessage.text = ""
                    for (i in t){
                        binding.tvMvvmRxMessage.append("title : " + i.title+"\n\n\n\n")
                    }
                    showToast(requireContext(),"عملیات با موفقیت انجام شد")
                }

                override fun onError(e: Throwable) {
                    showToast(requireContext(),e.message.toString())
                }
            })

    }


    override fun onDestroy() {
        super.onDestroy()
        if (compositeDisposable!= null) compositeDisposable.clear()
    }

}