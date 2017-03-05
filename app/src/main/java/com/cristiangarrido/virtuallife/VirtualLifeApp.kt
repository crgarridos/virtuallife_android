package com.cristiangarrido.virtuallife

import android.app.Application
import com.cristiangarrido.virtuallife.base.BaseActivity
import com.cristiangarrido.virtuallife.core.AppComponent
import com.cristiangarrido.virtuallife.core.ContextModule
import timber.log.Timber

/**
 * Created by cristian on 03/03/17.
 */
class VirtualLifeApp : Application() {

    lateinit var component: AppComponent
    private set


    override fun onCreate() {
        super.onCreate()

        // initialize Timber in debug version to log
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                // adding line number to logs
                return super.createStackElementTag(element) + ":" + element.lineNumber
            }
        })

        component = DaggerAppComponent.builder()
                .contextModule(ContextModule(this))
                .build()


//        Picasso.setSingletonInstance(picasso)//Don't do this. Isn't proper Dependency Injection



    }
}