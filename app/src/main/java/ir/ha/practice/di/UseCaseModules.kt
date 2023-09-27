package ir.ha.practice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.ha.practice.repository.DeveloperRepository
import ir.ha.practice.repository.DeveloperRepositoryImpl
import ir.ha.practice.services.http.ApiService
import ir.ha.practice.usecases.DeveloperUseCase
import ir.ha.practice.usecases.DeveloperUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModules {

    @Singleton
    @Provides
    fun provideDeveloperUseCase(developerRepository: DeveloperRepository) : DeveloperUseCase{
        return DeveloperUseCaseImpl(developerRepository)
    }

}