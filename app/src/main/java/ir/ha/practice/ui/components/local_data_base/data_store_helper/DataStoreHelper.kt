package ir.ha.practice.ui.components.local_data_base.data_store_helper

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ir.ha.practice.utility.data_store.DataStorePreferences
import ir.ha.practice.utility.data_store.DataStorePreferencesImpl
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class DataStoreHelper @Inject constructor(
    private val dataStore : DataStore<Preferences>,
    private val scope : CoroutineScope
) : DataStorePreferences by DataStorePreferencesImpl(dataStore) {

    private val TAG = "DATA_STORE_TAG"

    fun saveTheme() {
        Log.i(TAG, "saveTheme: ")
    }

    fun getTheme(){
        Log.i(TAG, "getTheme: ")
    }

    fun removeAllData(shouldRemoveConfig: Boolean) {
        Log.i(TAG, "removeAllData: shouldRemoveConfig: $shouldRemoveConfig")

        if (shouldRemoveConfig) {
            Log.d(TAG, "removeAllData:")
            remove(PreferencesKeys.baseUrl)
            remove(PreferencesKeys.themeKey)
        }

        remove(PreferencesKeys.baseUrl)
        remove(PreferencesKeys.baseUrl)
    }



    // KEYS
    private object PreferencesKeys {
        val baseUrl = stringPreferencesKey("baseUrl")
        val themeKey = stringPreferencesKey("profileCreditInfoEntity")
        val sharedSecretKey = stringPreferencesKey("sharedSecretKey")
        val onlineShops = stringPreferencesKey("onlineShops")
    }


}