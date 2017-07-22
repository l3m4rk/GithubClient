package example.l3m4rk.edu.githubclient.di

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import example.l3m4rk.edu.githubclient.di.login.LoginComponent
import example.l3m4rk.edu.githubclient.di.user.UserComponent
import example.l3m4rk.edu.githubclient.presentation.login.views.LoginActivity
import example.l3m4rk.edu.githubclient.presentation.user.views.UserActivity


@Module
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity::class)
    abstract fun provideLoginActivityInjector(builder: LoginComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(UserActivity::class)
    abstract fun provideUserActivityInjector(builder: UserComponent.Builder): AndroidInjector.Factory<out Activity>

}