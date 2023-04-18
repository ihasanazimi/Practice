package ir.ha.practice.ui.tabs.components_tab.firebase

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.google.firebase.messaging.FirebaseMessaging
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentFirebaseBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.dataStore
import ir.ha.practice.utility.extentions.showToast
import kotlinx.coroutines.flow.first

class FirebaseCloudMessagingFragment : BaseFragment<FragmentFirebaseBinding>() {
    override val layoutId: Int get() = R.layout.fragment_firebase
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            FirebaseMessaging.getInstance().token.addOnSuccessListener { token ->
                Log.d("xxxx token" , token)
                lifecycleScope.launchWhenCreated {
                    val savedToken = readFromPref("fireBase_token_key")
                    if (savedToken != null && token == savedToken){
                        binding.tokenTV.text = readFromPref("fireBase_token_key")
                        showToast(requireContext(),"read token from file")
                    }else {
                        binding.tokenTV.text = token
                        saveInput("fireBase_token_key",token)
                        showToast(requireContext(),"saved new token")
                    }
                }
            }
        } catch (e: Throwable) { showToast(requireContext(),e.message.toString()) }
    }


    // read from DataStore
    private suspend fun readFromPref(key : String ) : String?{
        val dataStoreKey = stringPreferencesKey(key)
        val preference = requireContext().dataStore.data.first()
        return preference[dataStoreKey]
    }

    // write into DataStore
    private suspend fun saveInput(key : String, value : String) {
        val dataStoreKey = stringPreferencesKey(key)
        requireContext().dataStore.edit { settings ->
            settings[dataStoreKey] = value/* this is value */
        }
    }
}