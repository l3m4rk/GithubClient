package example.l3m4rk.edu.githubclient.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import example.l3m4rk.edu.githubclient.repositories.user.IUserRepository
import example.l3m4rk.edu.githubclient.repositories.user.UserRepository

@Module
class AppModule {

    @Provides fun provideUserRepository(appContext: Context): IUserRepository {
        return UserRepository(appContext)
    }

    @Provides fun provideToken(userRepository: IUserRepository): String {
        return userRepository.loadUser().blockingGet().authToken
    }

    @Provides fun provideContext(application: Application): Context {
        return application.applicationContext
    }



}