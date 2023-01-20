package ir.ha.practice.utility.base

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ir.ha.practice.utility.LoadingFragment
import ir.ha.practice.utility.util.localizedContext

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    private var _binding: V? = null
    val binding get() = _binding!!

    private lateinit var loadingFrg : LoadingFragment

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,layoutId)
        initWhenUiCreated()
        clickEvents()
    }

    open fun clickEvents(){}
    open fun initWhenUiCreated(){}

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(localizedContext(context))
    }

    override fun onStart() {
        super.onStart()
        localizedContext(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    open fun showLoading(container : Int){
        loadingFrg = LoadingFragment()
        supportFragmentManager.beginTransaction().addToBackStack("loadingFrg")
            .replace(container, loadingFrg, "loadingFrg").commit()
    }

    open fun hideLoading(){
        if (::loadingFrg.isInitialized) supportFragmentManager.beginTransaction().remove(loadingFrg).commit()
    }
}