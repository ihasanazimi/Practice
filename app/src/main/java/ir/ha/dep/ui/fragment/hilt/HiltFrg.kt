package ir.ha.dep.ui.fragment.hilt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentHiltBinding
import ir.ha.dep.ui.BaseFragment2
import ir.ha.dep.utility.extentions.showToast
import javax.inject.Inject
import javax.inject.Named


@AndroidEntryPoint
class HiltFrg :  BaseFragment2<FragmentHiltBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_hilt

    @Inject
    lateinit var testClass2 : TestClass2


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        showToast(requireContext(),testClass.logTestMessage())
        showToast(requireContext(),testClass2.logTestMessage2())
    }

}



class TestClass
@Inject
constructor(){

    fun logTestMessage() : String = "this is test msg"

}

@ActivityScoped
class TestClass2
@Inject
constructor(private val testCls : TestClass){
    fun logTestMessage2() : String = testCls.logTestMessage()
}