//import android.app.Dialog
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.WindowManager
//import android.widget.FrameLayout
//import androidx.annotation.LayoutRes
//import androidx.databinding.DataBindingUtil
//import androidx.databinding.ViewDataBinding
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.google.android.material.bottomsheet.BottomSheetDialog
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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



sealed class ApiResult<out T> {

    data class Success<out T>(val data: T) : ApiResult<T>()

    data class Error<out T>(val exception: Exceptions, val data: T? = null) : ApiResult<T>()
}





sealed class Exceptions {

    data class IOException(val message: String = "IO Exception", val cause: Throwable) :
        Exceptions()

    data class NetworkConnectionException(val message: String = "Network Connection Error") :
        Exceptions()

    data class RemoteDataSourceException(
        val status: String?,
        val message: String?,
        val responseCode: Int
    ) : Exceptions()

    data class LocalDataSourceException(
        val message: String = "Local Data Source Error",
        val cause: Throwable? = null
    ) :
        Exceptions()

    data class InputDataException<T : Any>(val errors: List<T>) : Exceptions()

    data class ValidationException<T : Any>(val errors: T) : Exceptions()
}


