package ir.ha.practice

import android.app.Application
import android.content.Context
import android.os.Handler
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp
import ir.ha.practice.services.fresco.ImageLoadingService
import ir.ha.practice.services.fresco.ImageLoadingServiceImpl
import ir.ha.practice.services.http.ApiService
import ir.ha.practice.ui.components.broadcast_eventbus.MyEvent
import ir.ha.practice.ui.components.local_data_base.room.core.RoomDB
import ir.ha.practice.utility.extentions.changeTheme
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
        changeTheme(false)

        /** init fresco */
        Fresco.initialize(this)

        /** Init Timber
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())*/


        /** 1- Koin -> modules.. */
        val myModules = module {
            single { ApiService().api }
            factory<ImageLoadingService> { ImageLoadingServiceImpl() } // fresco
            single { RoomDB.getDataBase(this@App) }
        }

        /** 2- Start Coin By Modules... */
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }

        /** eventBus publisher */
        timer = Timer().also { it.schedule(object : TimerTask(){
                override fun run() {
                    EventBus.getDefault().post(MyEvent())
                        } },0,4000)
        }
    }
}