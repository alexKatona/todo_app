package sk.alex_katona.todo_app.base

import android.app.Application
import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.config.rainbowCake
import dagger.hilt.android.HiltAndroidApp
import sk.alex_katona.todo_app.managers.AppActivityManager
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application() {

    @Inject
    lateinit var appActivityManager: AppActivityManager

    override fun onCreate() {
        super.onCreate()
        appActivityManager.registerActivityLifecycleHelper(this)
        Timber.plant(Timber.DebugTree())
        rainbowCake {
            isDebug = true
            logger = Loggers.ANDROID
            consumeExecuteExceptions = false
        }
    }
}