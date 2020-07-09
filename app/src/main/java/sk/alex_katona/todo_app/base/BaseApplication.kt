package sk.alex_katona.todo_app.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.hilt.android.HiltAndroidApp
import sk.alex_katona.todo_app.managers.AppActivityManager
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application() {

    @Inject lateinit var appActivityManager: AppActivityManager

    override fun onCreate() {
        super.onCreate()
        appActivityManager.registerActivityLifecycleHelper(this)
    }
}