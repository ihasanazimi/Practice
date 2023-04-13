package ir.ha.practice.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.ha.practice.repository.DeveloperRepository
import ir.ha.practice.repository.DeveloperRepositoryImpl
import ir.ha.practice.services.http.ApiService
import ir.ha.practice.usecases.DeveloperUseCase
import ir.ha.practice.usecases.DeveloperUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun provideDeveloperUseCase(developerRepository: DeveloperRepository) : DeveloperUseCase{
        return DeveloperUseCaseImpl(developerRepository)
    }


    @Singleton
    @Provides
    fun provideDeveloperRepository(apiService: ApiService) : DeveloperRepository{
        return DeveloperRepositoryImpl(apiService)
    }

}