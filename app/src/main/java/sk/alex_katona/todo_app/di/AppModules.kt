package sk.alex_katona.todo_app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import sk.alex_katona.todo_app.managers.AppActivityManager
import sk.alex_katona.todo_app.managers.AppActivityManagerImpl
import sk.alex_katona.todo_app.navigators.AppNavigator
import sk.alex_katona.todo_app.navigators.AppNavigatorImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModules {

    @Singleton
    @Binds
    abstract fun bindAppActivityManager(appActivityManagerImpl: AppActivityManagerImpl): AppActivityManager

    @Singleton
    @Binds
    abstract fun bindAppNavigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator

}