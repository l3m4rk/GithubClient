package example.l3m4rk.edu.githubclient.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import example.l3m4rk.edu.githubclient.App

@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        BuildersModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
    
}

