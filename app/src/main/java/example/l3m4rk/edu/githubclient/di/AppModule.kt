package example.l3m4rk.edu.githubclient.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import example.l3m4rk.edu.githubclient.repositories.user.IUserRepository
import example.l3m4rk.edu.githubclient.repositories.user.UserRepository
import example.l3m4rk.edu.githubclient.services.auth.AuthHolder
import example.l3m4rk.edu.githubclient.services.auth.IAuthHolder

@Module
class AppModule {

    @Provides fun provideUserRepository(appContext: Context): IUserRepository {
        return UserRepository(appContext)
    }

    @Provides fun provideAuthHolder(context: Context): IAuthHolder {
        return AuthHolder(context)
    }

    @Provides fun provideContext(application: Application): Context {
        return application.applicationContext
    }



}