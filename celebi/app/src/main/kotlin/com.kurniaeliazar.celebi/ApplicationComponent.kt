package com.kurniaeliazar.celebi

import dagger.Component
import javax.inject.Singleton

/**
 * Created by kurniaeliazar on 12/22/16.
 */

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class
//        NetworkModule::class,
//        ApiModule::class
))

interface ApplicationComponent {

    // Injectors
    fun injectTo(app: CelebiApplication)

    // Submodule methods
    // Every screen is its own submodule of the graph and must be added here.
//    fun plus(module: ListModule): ListComponent
//    fun plus(module: DetailModule): DetailComponent
}