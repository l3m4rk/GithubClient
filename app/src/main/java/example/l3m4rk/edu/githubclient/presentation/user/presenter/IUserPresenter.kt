package example.l3m4rk.edu.githubclient.presentation.user.presenter

import example.l3m4rk.edu.githubclient.presentation.user.views.UserView

interface IUserPresenter {

    fun bindView(userView: UserView)

    fun checkUserLoggedIn()

    fun unbindView()

}