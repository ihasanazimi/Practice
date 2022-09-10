package ir.ha.dummy

import android.app.Application
import android.content.Context
import android.os.Handler
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp
import ir.ha.dummy.repo.RoomDB
import ir.ha.dummy.services.ImageLoadingService
import ir.ha.dummy.services.ImageLoadingServiceImpl
import ir.ha.dummy.services.http.MyApiService
import ir.ha.dummy.services.http.apiService
import ir.ha.dummy.ui.fragment.MyEvent
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.*

@HiltAndroidApp
class App : Application() {

    companion object{
        var context :Context ?= null
        var timer :Timer ?= null
        lateinit var applicationHandler : Handler
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        applicationHandler = Handler(this.mainLooper)

        /** 1- Koin -> modules.. */
        val myModules = module {
            single<MyApiService> { apiService }
            single<ImageLoadingService> { ImageLoadingServiceImpl() } // fresco
            factory<RoomDB> { RoomDB.getDataBase(applicationContext) }
        }


        /** 2- Start Coin By Modules... */
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }


        /** initialize fresco */
        Fresco.initialize(this)
        RoomDB.getDataBase(this)


        /** eventBus publisher */
        timer = Timer().also {
            it.schedule(object : TimerTask(){
                override fun run() {
                    EventBus.getDefault().post(MyEvent())
                } },0,4000)
        }
    }

}


// Singleton Data Store
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")