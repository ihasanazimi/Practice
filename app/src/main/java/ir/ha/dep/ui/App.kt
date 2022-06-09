package ir.ha.dep.ui

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import ir.ha.dep.repo.dataSource.BannerLocalDataSource
import ir.ha.dep.repo.BannerRepository
import ir.ha.dep.repo.BannerRepositoryImpl
import ir.ha.dep.services.ImageLoadingService
import ir.ha.dep.services.ImageLoadingServiceImpl
import ir.ha.dep.services.http.MyApiService
import ir.ha.dep.services.http.apiService
import ir.ha.dep.ui.viewModels.SampleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        /** initialize fresco */
        Fresco.initialize(this)


        /** 1- Koin -> modules.. */
        val myModules = module {
            single<MyApiService> { apiService }
            factory<BannerRepository> { BannerRepositoryImpl(BannerLocalDataSource()) }
            single<ImageLoadingService> { ImageLoadingServiceImpl() } // fresco
            viewModel{ SampleViewModel(get()) }
        }

        /** Start Coin By Modules... */
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }


    }
}