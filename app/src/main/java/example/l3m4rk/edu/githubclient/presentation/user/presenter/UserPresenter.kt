package example.l3m4rk.edu.githubclient.presentation.user.presenter

import example.l3m4rk.edu.githubclient.business.user.IUserInteractor
import example.l3m4rk.edu.githubclient.data.app.Result
import example.l3m4rk.edu.githubclient.data.app.user.User
import example.l3m4rk.edu.githubclient.presentation.user.views.UserView

class UserPresenter(private val userInteractor: IUserInteractor) : IUserPresenter {

    private var userView: UserView? = null

    override fun bindView(userView: UserView) {
        this.userView = userView
    }

    override fun checkUserLoggedIn() {
        userInteractor.checkUserLoggedIn().subscribe({
            when (it) {
                is Result.Fail -> userView?.showLoginScreen()
                is Result.Success -> userView?.showUser(User("","","",""))
            }
        }, {
            //error
        })

    }

    override fun unbindView() {
        this.userView = null
    }

}