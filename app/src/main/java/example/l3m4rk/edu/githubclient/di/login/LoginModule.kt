package example.l3m4rk.edu.githubclient.di.login

import dagger.Module
import dagger.Provides
import example.l3m4rk.edu.githubclient.business.login.ILoginInteractor
import example.l3m4rk.edu.githubclient.business.login.LoginInteractor
import example.l3m4rk.edu.githubclient.presentation.login.presenter.ILoginPresenter
import example.l3m4rk.edu.githubclient.presentation.login.presenter.LoginPresenter
import example.l3m4rk.edu.githubclient.repositories.user.IUserRepository
import example.l3m4rk.edu.githubclient.services.GithubService
import example.l3m4rk.edu.githubclient.services.auth.IAuthHolder

@Module
class LoginModule {

    @Provides fun provideLoginPresenter(loginInteractor: ILoginInteractor): ILoginPresenter {
        return LoginPresenter(loginInteractor)
    }

    @Provides fun provideLoginInteractor(githubService: GithubService,
                                         userRepository: IUserRepository,
                                         authHolder: IAuthHolder): ILoginInteractor {
        return LoginInteractor(githubService, userRepository, authHolder)
    }


}

