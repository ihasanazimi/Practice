package ir.ha.practice.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModules {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext appContext: Context): Context = appContext


}