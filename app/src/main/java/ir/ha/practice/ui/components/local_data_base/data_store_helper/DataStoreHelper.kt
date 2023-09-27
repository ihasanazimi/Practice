package ir.ha.practice.ui.components.local_data_base.data_store_helper

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import ir.ha.practice.utility.data_store.DataStorePreferences
import ir.ha.practice.utility.data_store.DataStorePreferencesImpl
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DataStoreHelper @Inject constructor(
    private val dataStore : DataStore<Preferences>,
    private val scope : CoroutineScope
) : DataStorePreferences by DataStorePreferencesImpl(dataStore) {

    private val TAG = "DATA_STORE_TAG"


    fun saveTheme(){
        Log.i(TAG, "saveTheme: ")
    }





    // KEYS
    // todo write later keys

}