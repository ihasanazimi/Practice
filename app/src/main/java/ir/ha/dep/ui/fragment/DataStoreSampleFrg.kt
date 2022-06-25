package ir.ha.dep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import ir.ha.dep.R
import ir.ha.dep.databinding.FragmentDataStoreSampleBinding
import ir.ha.dep.ui.BaseFragment
import ir.ha.dep.utility.extentions.dataStore
import kotlinx.coroutines.flow.first

class DataStoreSampleFrg : BaseFragment() {

    private lateinit var binding  : FragmentDataStoreSampleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = getBinding(R.layout.fragment_data_store_sample,container!!)
        return binding.root
    }


    override fun onResume() {
        super.onResume()

        lifecycleScope.launchWhenResumed {
            binding.input.setText(readFromPref("test_key"))
        }


        binding.btn.setOnClickListener{
            binding.input.setText(binding.input.text.toString())
            lifecycleScope.launchWhenResumed {
                saveInput("test_key" , binding.input.text.toString())
            }
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