package ir.ha.dep.ui

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.Preferences
import com.facebook.drawee.backends.pipeline.Fresco
import ir.ha.dep.repo.RoomDB
import ir.ha.dep.services.ImageLoadingService
import ir.ha.dep.services.ImageLoadingServiceImpl
import ir.ha.dep.services.http.MyApiService
import ir.ha.dep.services.http.apiService
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    var context :Context ?= null

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext

        /** initialize fresco */
        Fresco.initialize(this)
        RoomDB.getDataBase(this)

//        /** 1- Koin -> modules.. */
//        val myModules = module {
//            single<MyApiService> { apiService }
//            single<ImageLoadingService> { ImageLoadingServiceImpl() } // fresco
//            factory<RoomDB> { RoomDB.getDataBase(applicationContext) }
//        }
//
//        /** 2- Start Coin By Modules... */
//        startKoin {
//            androidContext(this@App)
//            modules(myModules)
//        }


    }
}