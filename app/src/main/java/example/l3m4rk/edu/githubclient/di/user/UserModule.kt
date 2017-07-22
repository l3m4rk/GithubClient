package example.l3m4rk.edu.githubclient.di.user

import dagger.Module
import dagger.Provides
import example.l3m4rk.edu.githubclient.business.user.IUserInteractor
import example.l3m4rk.edu.githubclient.business.user.UserInteractor
import example.l3m4rk.edu.githubclient.presentation.user.presenter.IUserPresenter
import example.l3m4rk.edu.githubclient.presentation.user.presenter.UserPresenter
import example.l3m4rk.edu.githubclient.repositories.user.IUserRepository
import example.l3m4rk.edu.githubclient.services.auth.IAuthHolder

@Module
class UserModule {

    @Provides fun provideUserInteractor(userRepository: IUserRepository, authHolder: IAuthHolder): IUserInteractor {
        return UserInteractor(userRepository, authHolder)
    }

    @Provides fun provideUserPresenter(userInteractor: IUserInteractor): IUserPresenter {
        return UserPresenter(userInteractor)
    }
}