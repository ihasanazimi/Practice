package ir.ha.dummy.ui.fragment.rxjava

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.google.android.material.chip.Chip
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentRxJavaContainerBinding
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.hide
import ir.ha.dummy.utility.extentions.show
import ir.ha.dummy.utility.extentions.showToast
import java.io.IOException
import java.util.concurrent.TimeUnit

class RxJavaContainer : BaseFragment<FragmentRxJavaContainerBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_rx_java_container
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var disposable: Disposable // یعنی یه چیز دور ریختنی

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pb.hide()

        disposable = CompositeDisposable()
        compositeDisposable = CompositeDisposable()

        val chips = arrayOf<String>(
            "just",
            "filter",
            "skip",
            "take",
            "takeLast",
            "map",
            "flatMap",
            "concatMap",
            "switchMap",
            "interval operator",
            "timer operator",
            "multi operators",
//            "defer",
//            "create",
//            "interval",
//            "range",
//            "repeat",
//            "start",
//            "timer",
//            "buffer",
//            "flatMap",
//            "GroupBy",
//            "map",
//            "scan"
        )
        chips.forEach {
            createChip(it)
        }


        binding.clearText.setOnClickListener {
            negativeMode()
            removeCurrentOperatorTextOnTV()
            clearCheckedChips()
            disposable.dispose()
        }
        binding.chipGroupRxJavaContainer[0].setOnClickListener { justOperator(chips[0]) }
        binding.chipGroupRxJavaContainer[1].setOnClickListener { filter(chips[1]) }
        binding.chipGroupRxJavaContainer[2].setOnClickListener { skip(chips[2]) }
        binding.chipGroupRxJavaContainer[3].setOnClickListener { take(chips[3]) }
        binding.chipGroupRxJavaContainer[4].setOnClickListener { takeLast(chips[4]) }
        binding.chipGroupRxJavaContainer[5].setOnClickListener { map(chips[5]) }
        binding.chipGroupRxJavaContainer[6].setOnClickListener { flatMap(chips[6]) }
        binding.chipGroupRxJavaContainer[7].setOnClickListener { concatMap(chips[7]) }
        binding.chipGroupRxJavaContainer[8].setOnClickListener { switchMap(chips[8]) }
        binding.chipGroupRxJavaContainer[9].setOnClickListener { intervalOperator(chips[9]) }
        binding.chipGroupRxJavaContainer[10].setOnClickListener { timerOperator(chips[10]) }
        binding.chipGroupRxJavaContainer[11].setOnClickListener { multiOperator(chips[11]) }
    }




    private fun createChip(txt: String) {
        Chip(requireContext()).apply {
            text = txt
            isCheckable = true
            binding.chipGroupRxJavaContainer.addView(this)
        }
    }




    private fun justOperator(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        getMyObservable()
            .doFinally { requireActivity().runOnUiThread{binding.pb.hide()} }
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun filter(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)

        getMyObservable()
            .doFinally { requireActivity().runOnUiThread{binding.pb.hide()} }
            .filter { x -> x > 2 } // by lambda expression
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun skip(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)

        getMyObservable()
            .doFinally { requireActivity().runOnUiThread{binding.pb.hide()} }
            .skip(3) // by lambda expression - yani 3 taye aval ro dar nazar naghir
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun take(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)

        getMyObservable()
            .doFinally { requireActivity().runOnUiThread{binding.pb.hide()} }
            .take(3) // by lambda expression - yani 3 taye aval ro fagat barghardoon - barAx skip
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun takeLast(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        getMyObservable()
            .doFinally { requireActivity().runOnUiThread{binding.pb.hide()} }
            .take(3) // by lambda expression - yani 3 taye akhar ro fagat barghardoon
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun map(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        getMyObservable()
            .doFinally { requireActivity().runOnUiThread{binding.pb.hide()} }
            .map { n -> "$n conveted to Sting " }
            // tarkib va tabdil mikone be String masalan  (karbordi)
            // masalan unja mishe fName va lName ro bechasbuni be ham va khuruji beghiri
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: String) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun flatMap(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        getMyObservable()
            .doFinally { requireActivity().runOnUiThread{binding.pb.hide()} }
            .flatMap { n -> Observable.just(n * 10).delay(1, TimeUnit.SECONDS) }
            // tafavof ba map mishe inke flatMap bejaye n - obserable E az n barmighardoone ba delay
            // emit shodan item ha monazam nist
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun concatMap(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        getMyObservable()
            .doFinally { requireActivity().runOnUiThread{binding.pb.hide()} }
            .concatMap { n -> Observable.just(n * 10).delay(1, TimeUnit.SECONDS) }
            //  obserable E az n barmighardoone ba delay
            // emit shodan item ha monazam haaaaaast va tartiz zamani barAxe flatMap monazam hastan
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun switchMap(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        getMyObservable()
            .doFinally { requireActivity().runOnUiThread{binding.pb.hide()} }
            .switchMap { n -> Observable.just(n * 10).delay(1, TimeUnit.SECONDS) }
            //  hame observable haro dooz mirize va fagat akharin observable ro return mikone
            // emit shodan item ha monazam haaaaaast va tartiz zamani barAxe flatMap monazam hastan
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun intervalOperator(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        Observable
            .interval(0, 1, TimeUnit.SECONDS)
            // jayGozin dorost baraye timer android
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Long) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun timerOperator(operatorName: String) {

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)

        Observable
            .timer(3, TimeUnit.SECONDS)
            // timer ya alarm set mikone k masalan bad az 3 seconds biyad ye kario anjam bede va emit kone
            // dar kol miyad ye kario ba ye delay anjam mide
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Long) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })
    }

    private fun multiOperator(operatorName: String) {

        // switchMap ya switchMap baraye Obserable karbord dare na single!

        binding.pb.show()
        removeCurrentOperatorTextOnTV()
        addCurrentOperatorTitleOnTV(operatorName)


        getMyObservable().filter { n -> n > 2 }
            .doFinally { requireActivity().runOnUiThread{binding.pb.hide()} }
            .map { n -> n * 10 }
            .concatMap { n -> Observable.just(n * 10).delay(1, TimeUnit.SECONDS) }
            .doFinally {
                showToast(requireContext(),"DO Finally is called!")
            }
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    binding.message.append(t.toString() + "\n")
                }

                override fun onError(e: Throwable) {
                    errorMode(e)
                }

                override fun onComplete() {
                    successMode()
                }
            })

    }




    private fun getMyObservable(): Observable<Int> {
        return Observable.create { emmiter ->
            try {
                // hamoon kar just ro anjam mide -> 1..20
                for (i in 0..20) {
                    if (!emmiter.isDisposed) {
                        emmiter.onNext(i)
                    }
                }

                if (emmiter.isDisposed) emmiter.onComplete()

            } catch (e: IOException) {
                emmiter.onError(e)
            }
        }
    }




    private fun clearCheckedChips() = binding.chipGroupRxJavaContainer.clearCheck()
    private fun removeCurrentOperatorTextOnTV() {
        binding.currentOperator.setText("")
    }
    private fun addCurrentOperatorTitleOnTV(operatorName: String) = binding.currentOperator.setText(operatorName)



    fun errorMode(e: Throwable) {
        binding.resultMessage.text = e.message.toString()
        binding.resultMessage.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.red
            )
        )
    }



    fun successMode() {
        binding.resultMessage.text = "Completed :)"
        binding.resultMessage.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.complete
            )
        )
        disposable.dispose()
        requireActivity().runOnUiThread{binding.pb.hide()}
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