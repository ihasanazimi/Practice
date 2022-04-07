package ir.ha.myapplication

import android.app.Application
import ir.ha.myapplication.main.SampleViewModel
import ir.ha.myapplication.services.MyApiService
import ir.ha.myapplication.services.apiService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModules = module {
            single<MyApiService> { apiService }
            viewModel{ SampleViewModel() }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }


    }
}