package ir.ha.dummy.ui.fragment.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("Str1")
    fun provideStringTest() : String {
        return "this is string test message"
    }
}