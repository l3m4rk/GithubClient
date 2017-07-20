package example.l3m4rk.edu.githubclient.presentation.login.presenter

import example.l3m4rk.edu.githubclient.presentation.login.views.LoginView

class LoginPresenter : ILoginPresenter {

    private var loginView: LoginView? = null

    override fun bindView(loginView: LoginView) {
        this.loginView = loginView
    }

    override fun login(login: String, password: String) {
        TODO("not implemented")
    }

    override fun unbindView() {
        loginView = null
    }


}