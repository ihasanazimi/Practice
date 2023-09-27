package ir.ha.practice.ui.components.firebase

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.google.firebase.messaging.FirebaseMessaging
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentFirebaseBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.showToast
import kotlinx.coroutines.launch

class FirebaseCloudMessagingFragment : BaseFragment<FragmentFirebaseBinding>() {
    override val layoutId: Int get() = R.layout.fragment_firebase
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            FirebaseMessaging.getInstance().token.addOnSuccessListener { token ->
                Log.d("xxxx token" , token)
                lifecycleScope.launch {
                    // todo
                }
            }
        } catch (e: Throwable) { showToast(requireContext(),e.message.toString()) }
    }

}