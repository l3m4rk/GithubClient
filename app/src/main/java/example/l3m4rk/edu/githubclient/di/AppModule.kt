package example.l3m4rk.edu.githubclient.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import example.l3m4rk.edu.githubclient.di.login.LoginModule
import example.l3m4rk.edu.githubclient.presentation.login.views.LoginActivity
import example.l3m4rk.edu.githubclient.presentation.user.views.UserActivity

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
    abstract fun provideLoginActivityInjector(): LoginActivity

    @ContributesAndroidInjector()
    abstract fun provideUserActivityInjector(): UserActivity

}