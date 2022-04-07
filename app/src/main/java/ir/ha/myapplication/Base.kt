package ir.ha.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ir.ha.myapplication.services.MyApiService
import org.koin.java.KoinJavaComponent.inject

abstract class BaseFragment : Fragment() , BaseView {
    override val rootView: ViewGroup?
        get() = view as ViewGroup?
    override val viewContext: Context?
        get() = context



}
abstract class BaseActivity : AppCompatActivity() , BaseView {
    override val rootView: ViewGroup?
        get() = window.decorView.rootView as ViewGroup
    override val viewContext: Context?
        get() = this


}

interface BaseView {

    val rootView : ViewGroup?
    val viewContext : Context?

    fun setProgressIndicator(mustShow : Boolean){
        rootView.let {
            viewContext.let { context ->
                var loadingBar = it?.findViewById<View>(R.id.loadingView)
                if (loadingBar == null && mustShow) {
                    loadingBar = LayoutInflater.from(context).inflate(R.layout.loading_bar, it, false) as FrameLayout?
                    rootView?.addView(loadingBar)
                }
                rootView?.visibility = if (mustShow) View.VISIBLE else  View.GONE
            }
        }
    }
}


abstract class BaseViewModel : ViewModel() {
    open val com = CompositeDisposable()
        get() = field


    override fun onCleared() {
        com.clear()
        super.onCleared()
    }
}