package com.kurniaeliazar.celebi
import android.app.Application
import com.kurniaeliazar.celebi.BuildConfig
import dagger.Lazy
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by kurniaeliazar on 12/22/16.
 */

class  CelebiApplication : Application(){

    @Inject
    lateinit var debugTree: Lazy<Timber.DebugTree>

    companion object{
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDependencyGraph()

//        if (BuildConfig.DEBUG) {
//            Timber.plant(debugTree.get())
//        }
    }

    private fun initDependencyGraph(){
//        graph = DaggerApplicationComponent.builder()
//                .applicationModule(com.kurniaeliazar.celebi.ApplicationModule(this))
//                .build()
//        graph.injectTo(this)
    }
}