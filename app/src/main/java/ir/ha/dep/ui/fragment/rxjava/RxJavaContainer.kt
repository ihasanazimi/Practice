package ir.ha.dep.ui.fragment.rxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.google.android.material.chip.Chip
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentRxJavaContainerBinding
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.showToast

class RxJavaContainer : BaseFragment() {

    private lateinit var binding : FragmentRxJavaContainerBinding
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getBinding(R.layout.fragment_rx_java_container,container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposable = CompositeDisposable()
        compositeDisposable = CompositeDisposable()

        val chips = arrayOf<String>("just" ,"defer","create" , "interval" , " range" , "repeat" , "start" , "timer" , "buffer" , "flatMap" , "GroupBy" , "map" , "scan" )
        chips.forEach {
            createChip(it)
        }


        binding.clearText.setOnClickListener{ negativeMode() }
        binding.chipGroupRxJavaContainer.get(0).setOnClickListener{ justOperator() }
        binding.chipGroupRxJavaContainer.get(2).setOnClickListener{}
    }




    private fun createChip(txt : String){
          Chip(requireContext()).apply {
            text = txt
            isCheckable = true
              binding.chipGroupRxJavaContainer.addView(this)
        }
    }




    private fun justOperator(){
        Observable.just("tiger" , "elephant" , "lion" , "bear")
            .subscribe(object : Observer<String>{
                override fun onSubscribe(d: Disposable) {
                    disposable.dispose()
                }

                override fun onNext(t: String) {
                    binding.message.append(t+"\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }






    fun errorMode(e: Throwable){
        binding.resultMessage.text = e.message.toString()
        binding.resultMessage.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.red))
    }

    fun successMode(){
        binding.resultMessage.text = "Completed :)"
        binding.resultMessage.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.complete))
    }

    private fun negativeMode() {
        binding.message.text = ""
        binding.resultMessage.text = ""
        binding.resultMessage.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.gray
            )
        )
    }





    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }
}