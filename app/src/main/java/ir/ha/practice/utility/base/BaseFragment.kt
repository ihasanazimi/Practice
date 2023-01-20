package ir.ha.practice.utility.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import ir.ha.practice.utility.LoadingFragment

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    private var _binding: V? = null
    val binding get() = _binding!!
    @get:LayoutRes
    abstract val layoutId: Int
    val mainHelper by lazy { (requireActivity()) }

    private lateinit var loadingFrg : LoadingFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvents()
        registerObservers()
    }


    open fun registerObservers(){}
    open fun clickEvents(){}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    open fun onScrollToTop() {}

    open fun onRetrievedTag(retrievedTag: String) {}

    open fun showLoading(container : Int){
        loadingFrg = LoadingFragment()
        mainHelper.supportFragmentManager.beginTransaction().addToBackStack("loadingFrg")
            .replace(container, loadingFrg, "loadingFrg").commit()
    }

    open fun hideLoading(){
        if (::loadingFrg.isInitialized) mainHelper.supportFragmentManager.beginTransaction().remove(loadingFrg).commit()
    }
}