package ir.ha.practice

import android.app.Application
import android.content.Context
import android.os.Handler
import com.facebook.drawee.backends.pipeline.Fresco
import ir.ha.practice.services.fresco.ImageLoadingService
import ir.ha.practice.services.fresco.ImageLoadingServiceImpl
import ir.ha.practice.services.http.ApiService
import ir.ha.practice.ui.main.fragment.broadcast_eventbus.MyEvent
import ir.ha.practice.ui.main.fragment.db.room.RoomDB
import ir.ha.practice.utility.util.ThemeUtils
import org.greenrobot.eventbus.EventBus
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.*

//@HiltAndroidApp
class ApplicationLoader : Application() {

    companion object{
        var context :Context ?= null
        var timer :Timer ?= null
        lateinit var applicationHandler : Handler
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        applicationHandler = Handler(this.mainLooper)
        ThemeUtils.changeTheme(false)

        /** init fresco */
        Fresco.initialize(this)

        /** Init Timber
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())*/


        /** 1- Koin -> modules.. */
        val myModules = module {
            single { ApiService().api }
            factory<ImageLoadingService> { ImageLoadingServiceImpl() } // fresco
            single { RoomDB.getDataBase(this@ApplicationLoader) }
        }

        /** 2- Start Coin By Modules... */
        startKoin {
            androidContext(this@ApplicationLoader)
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