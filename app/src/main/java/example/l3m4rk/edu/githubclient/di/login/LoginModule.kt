package example.l3m4rk.edu.githubclient.di.login

import dagger.Module
import dagger.Provides
import example.l3m4rk.edu.githubclient.presentation.login.presenter.ILoginPresenter
import example.l3m4rk.edu.githubclient.presentation.login.presenter.LoginPresenter

@Module
class LoginModule {

    @Provides fun provideLoginPresenter(): ILoginPresenter {
        return LoginPresenter()
    }

}

