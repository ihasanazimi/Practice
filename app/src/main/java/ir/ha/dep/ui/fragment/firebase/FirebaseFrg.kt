package ir.ha.dep.ui.fragment.firebase

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.google.firebase.messaging.FirebaseMessaging
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentFirebaseBinding
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.dataStore
import ir.ha.dep.utility.extentions.showToast
import kotlinx.coroutines.flow.first

class FirebaseFrg : BaseFragment() {

    private lateinit var binding : FragmentFirebaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getBinding(R.layout.fragment_firebase,container!!)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            FirebaseMessaging.getInstance().token.addOnSuccessListener { token ->
                Log.d("xxxx token" , token)

                lifecycleScope.launchWhenCreated {

                    if (readFromPref("fireBase_token_key") != null && readFromPref("fireBase_token_key")!!.isNotEmpty()
                        && token == readFromPref("fireBase_token_key")){
                        binding.tokenTV.text = readFromPref("fireBase_token_key")
                        showToast(requireContext(),"read token from file")
                    }else {
                        binding.tokenTV.text = token
                        saveInput("fireBase_token_key",token)
                        showToast(requireContext(),"saved new token")
                    }
                }
            }
        } catch (e: Throwable) {
            showToast(requireContext(),e.message.toString())
        }
    }


    private suspend fun readFromPref(key : String ) : String?{
        val dataStoreKey = stringPreferencesKey(key)
        val preference = requireContext().dataStore.data.first()
        return preference[dataStoreKey]
    }


    // // write into data Store
    private suspend fun saveInput(key : String, value : String) {
        val dataStoreKey = stringPreferencesKey(key)
        requireContext().dataStore.edit { settings ->
            settings[dataStoreKey] = value/* this is value */
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}