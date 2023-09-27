package ir.ha.practice.utility.data_store

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

interface DataStorePreferences {
    fun <T> read(key: Preferences.Key<T>): T?

    suspend fun <T> readAsync(key: Preferences.Key<T>): T?

    fun <T> save(key: Preferences.Key<T>, value: T)

    suspend fun <T> saveAsync(key: Preferences.Key<T>, value: T)

    fun <T> remove(key: Preferences.Key<T>)
}

class DataStorePreferencesImpl(
    private val dataStore: DataStore<Preferences>,
) : DataStorePreferences {

    val TAG = DataStorePreferencesImpl::class.java.simpleName

    override fun <T> read(key: Preferences.Key<T>): T? = runBlocking {
        Log.i(TAG, "read: data : ${dataStore.data.map { preferences -> preferences [key] }.first()} ")
        return@runBlocking dataStore.data.map { preferences -> preferences [key] }.first()
    }

    override suspend fun <T> readAsync(key: Preferences.Key<T>): T? {
        Log.i(TAG, "readAsync: data : ${dataStore.data.map { preferences -> preferences [key] }.first()} ")
        return dataStore.data.map { preferences -> preferences [key] }.first()
    }

    override fun <T> save(key: Preferences.Key<T>, value: T): Unit = runBlocking {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    override suspend fun <T> saveAsync(key: Preferences.Key<T>, value: T) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    override fun <T> remove(key: Preferences.Key<T>): Unit = runBlocking {
        dataStore.edit {
            it.remove(key)
        }
    }
}