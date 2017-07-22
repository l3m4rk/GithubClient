package example.l3m4rk.edu.githubclient.presentation.user.views

import example.l3m4rk.edu.githubclient.data.app.user.User

interface UserView {

    fun showUser(user: User)

    fun showLoginScreen()

}