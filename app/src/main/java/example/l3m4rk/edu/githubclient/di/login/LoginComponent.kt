package example.l3m4rk.edu.githubclient.di.login

import dagger.Subcomponent
import dagger.android.AndroidInjector
import example.l3m4rk.edu.githubclient.presentation.login.views.LoginActivity

@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent : AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<LoginActivity>()
}