package ir.ha.dep.ui.fragment.mvvm_rx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.dmoral.toasty.Toasty
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentMvvmRxJavaBinding
import ir.ha.dep.model.UserModel
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.showToast

class MVVMRxJavaFrg : BaseFragment() {

    private lateinit var binding : FragmentMvvmRxJavaBinding
    private lateinit var usersViewModel: UsersViewModel
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()
        usersViewModel = UsersViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = getBinding(R.layout.fragment_mvvm_rx_java,container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pb.visibility = View.VISIBLE


        usersViewModel.getNames()
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
                    Toasty.success(requireContext(),"با موفقیت انجام شد").show()
                }

                override fun onError(e: Throwable) {
                    Toasty.error(requireContext(),e.message.toString()).show()
                }
            })

    }


    override fun onDestroy() {
        super.onDestroy()
        if (compositeDisposable!= null) compositeDisposable.clear()
    }

}