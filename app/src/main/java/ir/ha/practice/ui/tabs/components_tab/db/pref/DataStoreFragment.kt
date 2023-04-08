package ir.ha.practice.ui.tabs.components_tab.db.pref

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentDataStoreBinding
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.dataStore
import kotlinx.coroutines.flow.first

class DataStoreFragment : BaseFragment<FragmentDataStoreBinding>() {
    override val layoutId: Int get() = R.layout.fragment_data_store


    override fun onResume() {
        super.onResume()

        lifecycleScope.launchWhenResumed { binding.input.setText(readFromPref("test_key")) }

        binding.btn.setOnClickListener{
            binding.input.setText(binding.input.text.toString())
            lifecycleScope.launchWhenResumed { saveInput("test_key" , binding.input.text.toString()) }
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

}