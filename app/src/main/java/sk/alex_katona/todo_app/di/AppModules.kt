package sk.alex_katona.todo_app.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import sk.alex_katona.todo_app.database.TodoDatabase
import sk.alex_katona.todo_app.managers.AppActivityManager
import sk.alex_katona.todo_app.managers.AppActivityManagerImpl
import sk.alex_katona.todo_app.mvvm.todo_list.TodoListInteractor
import sk.alex_katona.todo_app.mvvm.todo_list.TodoListInteractorImpl
import sk.alex_katona.todo_app.navigators.AppNavigator
import sk.alex_katona.todo_app.navigators.AppNavigatorImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindAppActivityManager(appActivityManagerImpl: AppActivityManagerImpl): AppActivityManager

    @Singleton
    @Binds
    abstract fun bindAppNavigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator

}

@Module
@InstallIn(ApplicationComponent::class)
abstract class FragmentModule {

    @Singleton
    @Binds
    abstract fun gindTodoListInteractor(todoListInteractorImpl: TodoListInteractorImpl): TodoListInteractor

}

@Module
@InstallIn(ApplicationComponent::class)
class EntitiesModule{

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(context, TodoDatabase::class.java, "todo_database")
            .fallbackToDestructiveMigration()
            .build()
    }

}