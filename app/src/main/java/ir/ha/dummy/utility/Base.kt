package ir.ha.dummy.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.disposables.CompositeDisposable
import ir.ha.dummy.R
import ir.ha.dummy.utility.localizedContext

abstract class BaseFragment : Fragment(), BaseView {
    override val rootView: ViewGroup?
        get() = view as ViewGroup?
    override val viewContext: Context?
        get() = context


    // default
    fun <T : ViewDataBinding> getBinding(layoutID: Int , parent : ViewGroup): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layoutID , parent , false)
    }

    // for custom views
    fun <T : ViewDataBinding> getBinding(layoutID: Int , parent : ViewGroup , attachToRoot : Boolean): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layoutID , parent ,attachToRoot)
    }
}

abstract class BaseFragment2<V : ViewDataBinding> : Fragment() {

    private var _binding: V? = null

    val binding get() = _binding!!

    val mainHelper by lazy { (requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @get:LayoutRes
    abstract val layoutId: Int

    open fun onScrollToTop() {}

    open fun onRetrievedTag(retrievedTag: String) {}
}


abstract class BaseActivity : AppCompatActivity(), BaseView {
    override val rootView: ViewGroup?
        get() = window.decorView.rootView as ViewGroup
    override val viewContext: Context?
        get() = this

    fun <T : ViewDataBinding?> getBinding(layoutID: Int): T {
        return DataBindingUtil.setContentView(this, layoutID)
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(localizedContext(context))
    }

    override fun onStart() {
        super.onStart()
        localizedContext(this)
    }
}


abstract class BaseDialogFragment<V : ViewDataBinding> :
    DialogFragment() {

    private var _binding: V? = null

    val binding get() = _binding!!

    val mainHelper by lazy { (requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @get:LayoutRes
    abstract val layoutId: Int

    open fun onScrollToTop() {}
}



abstract class BaseBottomSheetDialogFrg: BottomSheetDialogFragment() , BaseView {

    override val rootView: ViewGroup?
        get() = dialog?.window?.decorView?.parent as ViewGroup
    override val viewContext: Context?
        get() = this.requireContext()

    fun <T : ViewDataBinding?> getBinding(layout: Int): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layout, null, false)
    }

}



abstract class BaseDialogFrg : DialogFragment() , BaseView {

    override val rootView: ViewGroup?
        get() = dialog?.window?.decorView?.rootView as ViewGroup
    override val viewContext: Context?
        get() = this.requireContext()

    fun <T : ViewDataBinding?> getBinding(layout: Int): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layout, null, false)
    }

}



interface BaseView {

    val rootView: ViewGroup?
    val viewContext: Context?

    fun setProgressIndicator(mustShow: Boolean) {
        rootView.let {
            viewContext.let { context ->
                var loadingBar = it?.findViewById<View>(R.id.loadingView)
                if (loadingBar == null && mustShow) {
                    loadingBar = LayoutInflater.from(context).inflate(R.layout.loading_bar, it, false) as FrameLayout?
                    rootView?.addView(loadingBar)
                }
                rootView?.visibility = if (mustShow) View.VISIBLE else View.GONE
            }
        }
    }
}


abstract class BaseViewModel : ViewModel() {
    open val composable = CompositeDisposable()
        get() = field


    override fun onCleared() {
        composable.clear()
        super.onCleared()
    }
}





//abstract class BaseBottomSheetDialogFragment<V : ViewDataBinding> :
//    BottomSheetDialogFragment() {
//
//    private var _binding: V? = null
//
//    val binding get() = _binding!!
//
//    val mainHelper by lazy { (requireActivity()) }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
//        dialog.setOnShowListener { dialogs ->
//            val d = dialogs as BottomSheetDialog
//            val bottomSheet = d.findViewById<FrameLayout>(com.google.android.material.bottomsheet.BottomSheetDialog)
//            if (bottomSheet != null) {
//                BottomSheetBehavior.from(bottomSheet).apply {
//                    state = BottomSheetBehavior.STATE_EXPANDED
//                    skipCollapsed = true
//                }
//            }
//        }
//        dialog.window?.let {
//            if (showKeyboard) {
//                it.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
//            } else {
//                it.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
//            }
//        }
//        return dialog
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = DataBindingUtil.inflate(
//            inflater,
//            layoutId,
//            container,
//            false
//        )
//        return binding.root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    abstract val showKeyboard: Boolean
//
//    @get:LayoutRes
//    abstract val layoutId: Int
//
//    open fun onScrollToTop() {}
//}





//abstract class BaseViewModel : ViewModel() {
//
//    protected val coroutineContext = viewModelScope.coroutineContext + Dispatchers.IO
//
//    private val _networkViewState = MutableLiveData<NetworkViewState>()
//    val networkViewState: LiveData<NetworkViewState>
//        get() = _networkViewState
//
//    data class NetworkViewState(
//        var showProgress: Boolean,
//        var showProgressMore: Boolean,
//        var showSuccess: Boolean,
//        var showError: Boolean,
//        val showValidationError: Boolean,
//        val serverErrorMessage: String?,
//        val errorMessage: Int,
//        val errorIcon: Int,
//        val requestTag: String?,
//        val validationError: Any?,
//        val unauthorized: Boolean,
//        val data: Any?
//    )
//
//    private fun getNetworkViewState(
//        showProgress: Boolean = false,
//        showProgressMore: Boolean = false,
//        showSuccess: Boolean = false,
//        showError: Boolean = false,
//        showValidationError: Boolean = false,
//        serverErrorMessage: String? = null,
//        errorMessage: Int = R.string.error_general,
//        errorIcon: Int = R.drawable.ic_general_error,
//        requestTag: String? = null,
//        validationError: Any? = null,
//        unauthorized: Boolean = false,
//        data: Any? = null
//    ) = NetworkViewState(
//        showProgress,
//        showProgressMore,
//        showSuccess,
//        showError,
//        showValidationError,
//        serverErrorMessage,
//        errorMessage,
//        errorIcon,
//        requestTag,
//        validationError,
//        unauthorized,
//        data
//    )
//
//    private suspend fun emitNetworkViewState(
//        networkViewStates: NetworkViewState
//    ) {
//        withContext(Dispatchers.Main) {
//            _networkViewState.value = networkViewStates
//        }
//    }
//
//    protected suspend fun networkLoading(requestTag: String? = null) {
//        emitNetworkViewState(
//            getNetworkViewState(
//                showProgress = true,
//                requestTag = requestTag
//            )
//        )
//    }
//
//    protected suspend fun networkMoreLoading(requestTag: String? = null) {
//        emitNetworkViewState(
//            getNetworkViewState(
//                showProgressMore = true,
//                requestTag = requestTag
//            )
//        )
//    }
//
//    protected open suspend fun <T> observeNetworkState(
//        vararg results: ApiResult<T>,
//        requestTagList: List<String> = listOf()
//    ) {
//        var errorChecked = false
//        var networkStateList: List<NetworkViewState> = mutableListOf()
//
//        results.forEachIndexed { index, result ->
//
//            if (result is ApiResult.Error && !errorChecked) {
//                val networkViewState = getNetworkStateResult(result)
//                emitNetworkViewState(networkViewState)
//                errorChecked = true
//            }
//
//            // Check and get requestTag if existed
//            val requestTag = requestTagList.elementAtOrNull(index)
//
//            networkStateList = networkStateList.plus(getNetworkStateResult(result, requestTag))
//        }
//
//
//        // When all result become Success we have to handle them
//        if (!errorChecked)
//            emitNetworkViewState(
//                getNetworkViewState(showSuccess = true)
//            )
//    }
//
//    protected open suspend fun <T> observeNetworkState(result: ApiResult<T>, requestTag: String) {
//        emitNetworkViewState(getNetworkStateResult(result, requestTag))
//    }
//
//    private suspend fun <T> getNetworkStateResult(
//        result: ApiResult<T>,
//        requestTag: String? = null
//    ): NetworkViewState {
//        return when (result) {
//            is ApiResult.Success -> {
//                getNetworkViewState(
//                    showSuccess = true,
//                    data = castData(result, requestTag),
//                    requestTag = requestTag
//                )
//            }
//            is ApiResult.Error -> {
//                if (result.exception is Exceptions.ValidationException<*>) {
//                    getNetworkViewState(
//                        showValidationError = true,
//                        validationError = result.exception.errors,
//                        requestTag = requestTag
//                    )
//                } else {
//                    val errorView = ExceptionHelper.getError(result.exception)
//                    getNetworkViewState(
//                        showError = true,
//                        serverErrorMessage = errorView.serverErrorMessage,
//                        errorMessage = errorView.message,
//                        unauthorized = errorView.unauthorized,
//                        errorIcon = errorView.icon,
//                        requestTag = requestTag
//                    )
//                }
//            }
//        }
//    }
//
//    protected open suspend fun <T> castData(result: ApiResult<T>, requestTag: String?): Any? {
//        return (result as ApiResult.Success).data
//    }
//
//    open fun refresh() {
//        getData()
//    }
//
//    protected abstract fun getData()
//}
//
//
//
//
//
//sealed class ApiResult<out T> {
//
//    data class Success<out T>(val data: T) : ApiResult<T>()
//
//    data class Error<out T>(val exception: Exceptions, val data: T? = null) : ApiResult<T>()
//}





//sealed class Exceptions {
//
//    data class IOException(val message: String = "IO Exception", val cause: Throwable) :
//        Exceptions()
//
//    data class NetworkConnectionException(val message: String = "Network Connection Error") :
//        Exceptions()
//
//    data class RemoteDataSourceException(
//        val status: String?,
//        val message: String?,
//        val responseCode: Int
//    ) : Exceptions()
//
//    data class LocalDataSourceException(
//        val message: String = "Local Data Source Error",
//        val cause: Throwable? = null
//    ) :
//        Exceptions()
//
//    data class InputDataException<T : Any>(val errors: List<T>) : Exceptions()
//
//    data class ValidationException<T : Any>(val errors: T) : Exceptions()
//}


