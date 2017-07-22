package example.l3m4rk.edu.githubclient.presentation.login.presenter

import example.l3m4rk.edu.githubclient.presentation.login.views.LoginView

interface ILoginPresenter {

    fun bindView(loginView: LoginView)

    fun login(login: String, password: String)

    fun unbindView()

}