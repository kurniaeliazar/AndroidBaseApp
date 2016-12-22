package com.kurniaeliazar.celebi

/**
 * Created by kurniaeliazar on 12/22/16.
 */

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: CelebiApplication) {

    @Provides @Singleton
    fun provideApplication(): Application = app

    @Provides @Singleton @ApplicationQualifier
    fun provideContext(): Context = app.baseContext

    @Provides @Singleton
    fun provideResources(): Resources = app.resources

    @Provides @Singleton
    fun provideLayoutInflator(@ApplicationQualifier context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    @Provides
    fun provideDebugTree(): Timber.DebugTree = Timber.DebugTree()
}