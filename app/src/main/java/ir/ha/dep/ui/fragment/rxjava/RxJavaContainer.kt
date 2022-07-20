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
import java.util.concurrent.TimeUnit

class RxJavaContainer : BaseFragment() {

    private lateinit var binding : FragmentRxJavaContainerBinding
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var disposable: Disposable // یعنی یه چیز دور ریختنی

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

        val chips = arrayOf<String>("just" ,"filter","defer","create" , "interval" , " range" , "repeat" , "start" , "timer" , "buffer" , "flatMap" , "GroupBy" , "map" , "scan" )
        chips.forEach {
            createChip(it)
        }


        binding.clearText.setOnClickListener{
            negativeMode()
            removeCurrentOperatorTextOnTV()
            clearCheckedChips()
        }
        binding.chipGroupRxJavaContainer[0].setOnClickListener{ justOperator(chips[0]) }
        binding.chipGroupRxJavaContainer[1].setOnClickListener{ filter(chips[1])}
    }




    private fun createChip(txt : String){
          Chip(requireContext()).apply {
            text = txt
            isCheckable = true
            binding.chipGroupRxJavaContainer.addView(this)
        }
    }




    private fun justOperator(operatorName : String){

        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        Observable
            .just(1 , 2 , 3 , 4,5,6,7,8,9,10)
            .subscribe(object : Observer<Int>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString()+"\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }


    private fun filter(operatorName : String){

        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        Observable
            .just(1 , 2 , 3 , 4,5,6,7,8,9,10)
            .filter { x -> x > 2 } // by lambda expression
            .subscribe(object : Observer<Int>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString()+"\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun skip(operatorName : String){

        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        Observable
            .just(1 , 2 , 3 , 4,5,6,7,8,9,10)
            .skip(3) // by lambda expression - yani 3 taye aval ro dar nazar naghir
            .subscribe(object : Observer<Int>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString()+"\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun take(operatorName : String){

        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        Observable
            .just(1 , 2 , 3 , 4,5,6,7,8,9,10)
            .take(3) // by lambda expression - yani 3 taye aval ro fagat barghardoon - barAx skip
            .subscribe(object : Observer<Int>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString()+"\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun takeLast(operatorName : String){

        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        Observable
            .just(1 , 2 , 3 , 4,5,6,7,8,9,10)
            .take(3) // by lambda expression - yani 3 taye akhar ro fagat barghardoon
            .subscribe(object : Observer<Int>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString()+"\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun map(operatorName : String){

        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        Observable
            .just(1 , 2 , 3 , 4,5,6,7,8,9,10)
            .map { n->"number" + n }
            // tarkib va tabdil mikone be String masalan  (karbordi)
            // masalan unja mishe fName va lName ro bechasbuni be ham va khuruji beghiri
            .subscribe(object : Observer<String>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: String) {
                    binding.message.append(t.toString()+"\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun flatMap(operatorName : String){

        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        Observable
            .just(1 , 2 , 3 , 4,5,6,7,8,9,10)
            .flatMap { n -> Observable.just(n*10).delay(1,TimeUnit.SECONDS) }
            // tafavof ba map mishe inke flatMap bejaye n - obserable E az n barmighardoone ba delay
            // emit shodan item ha monazam nist
            .subscribe(object : Observer<Int>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString()+"\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun concatMapMap(operatorName : String){

        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        Observable
            .just(1 , 2 , 3 , 4,5,6,7,8,9,10)
            .concatMap { n -> Observable.just(n*10).delay(1,TimeUnit.SECONDS) }
            //  obserable E az n barmighardoone ba delay
            // emit shodan item ha monazam haaaaaast va tartiz zamani barAxe flatMap monazam hastan
            .subscribe(object : Observer<Int>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString()+"\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun switchMap(operatorName : String){

        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        Observable
            .just(1 , 2 , 3 , 4,5,6,7,8,9,10)
            .switchMap { n -> Observable.just(n*10).delay(1,TimeUnit.SECONDS) }
            //  hame observable haro dooz mirize va fagat akharin observable ro return mikone
            // emit shodan item ha monazam haaaaaast va tartiz zamani barAxe flatMap monazam hastan
            .subscribe(object : Observer<Int>{
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString()+"\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }


    private fun clearCheckedChips() = binding.chipGroupRxJavaContainer.clearCheck()
    private fun removeCurrentOperatorTextOnTV() = binding.currentOperator.setText("")
    private fun addCurrentOperatorTitleOnTV(operatorName : String) = binding.currentOperator.setText(operatorName)






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
        disposable.dispose() // for just operator
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }


//    api service operator by rxJava :
    /*
    single -> single object : hamishe monasebe - avalin obj ke mibine
    observable -> har objecti bebine mige - masaln vase download file estefade mishe
     */



    /* RX Schedulers items -> {
        io -> har baz be ezaye har task jadid miyad thread misaze (recommended)
        main -> ui thread ya hamoon main thrad
        newThread -> ye thread jadid misaze
        computation ->  be andaze core haye cpu thrad misaze k pishnehad nemishe
           }
     */
}