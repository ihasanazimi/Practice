package ir.ha.myapplication

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import ir.ha.myapplication.repo.datasource.BannerLocalDataSource
import ir.ha.myapplication.feacher.main.SampleViewModel
import ir.ha.myapplication.repo.BannerRepository
import ir.ha.myapplication.repo.BannerRepositoryImpl
import ir.ha.myapplication.services.ImageLoadingService
import ir.ha.myapplication.services.ImageLoadingServiceImpl
import ir.ha.myapplication.services.http.MyApiService
import ir.ha.myapplication.services.http.apiService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this);

        val myModules = module {
            single<MyApiService> { apiService }
            factory<BannerRepository> { BannerRepositoryImpl(BannerLocalDataSource()) }
            single<ImageLoadingService> { ImageLoadingServiceImpl() }
            viewModel{ SampleViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }


    }
}