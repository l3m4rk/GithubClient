package example.l3m4rk.edu.githubclient.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import example.l3m4rk.edu.githubclient.App

@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class
))
interface AppComponent {

    fun inject(app: App)
    
}

