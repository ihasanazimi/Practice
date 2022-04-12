package ir.ha.myapplication.util

//import android.app.Activity
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.ravaq.utils.view.CustomSnackbar

//fun Activity.snack(message: String,view:ViewGroup? = null) {
////    Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_LONG).show();
//    CustomSnackbar.make(
//        view?: findViewById(android.R.id.content) ?: window.decorView.rootView as ViewGroup, 6000
//    ).setText(message).show()
//
//}
//
//fun Activity.snackWarning(message: String) {
//    CustomSnackbar.makeWarning(
//        findViewById(android.R.id.content) ?: window.decorView.rootView as ViewGroup, 6000
//    ).setText(message).show()
//
//}
//
//fun Activity.snackWarning(message: String, actionTitle: String, listener: View.OnClickListener) {
//    CustomSnackbar.makeWarning(
//        findViewById(android.R.id.content) ?: window.decorView.rootView as ViewGroup, 6000
//    ).setText(message).setAction(actionTitle, listener).show()
//
//}
//
//
//fun Fragment.snack(message: String?) {
//    message ?: return
//    activity?.snack(message)
//
////    CustomSnackbar.make(view as ViewGroup,CustomSnackbar.LENGTH_LONG).setText(message).show()
//}
//
//
//fun Fragment.snackWarning(message: String?) {
//    message ?: return
//    activity?.snackWarning(message)
//
////    CustomSnackbar.make(view as ViewGroup,CustomSnackbar.LENGTH_LONG).setText(message).show()
//}
//
//fun snack(message: String?, view: ViewGroup) {
//    message ?: return
//    CustomSnackbar.make(view, 6000).setText(message).show()
//}
//
//
//fun ViewGroup.snack(message: String?) {
//    message ?: return
//    CustomSnackbar.make(findViewById(android.R.id.content) ?: this, 6000).setText(message).show()
//}
//
//
//fun ViewGroup.snackWarning(message: String?) {
//    message ?: return
//    CustomSnackbar.makeWarning(findViewById(android.R.id.content) ?: this, 6000).setText(message)
//        .show()
//}
//
//fun ViewGroup.snackWarning(message: String?, actionTitle: String, listener: View.OnClickListener) {
//    message ?: return
//    CustomSnackbar.makeWarning(findViewById(android.R.id.content) ?: this, 6000)
//        .setText(message)
//        .setAction(actionTitle, listener)
//        .show()
//}
//
//fun Activity.snack(message: String, actionTitle: String, listener: View.OnClickListener) {
//    CustomSnackbar
//        .make(findViewById(android.R.id.content) ?: window.decorView.rootView as ViewGroup, 6000)
//        .setText(message)
//        .setAction(actionTitle, listener)
//        .show()
//
//}
//
//
//fun Fragment.snack(message: String, actionTitle: String, listener: View.OnClickListener) {
//    activity?.snack(message, actionTitle, listener)
//}
//
//
//fun ViewGroup.snack(message: String, actionTitle: String, listener: View.OnClickListener) {
//    CustomSnackbar
//        .make(findViewById(android.R.id.content) ?: this, 6000)
//        .setText(message)
//        .setAction(actionTitle, listener)
//        .show()
//}

