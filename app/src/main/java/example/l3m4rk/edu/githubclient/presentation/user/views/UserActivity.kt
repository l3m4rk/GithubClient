package example.l3m4rk.edu.githubclient.presentation.user.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import example.l3m4rk.edu.githubclient.R
import example.l3m4rk.edu.githubclient.data.app.user.User
import example.l3m4rk.edu.githubclient.presentation.user.presenter.IUserPresenter
import javax.inject.Inject

class UserActivity : AppCompatActivity(), UserView {

    @Inject lateinit var userPresenter: IUserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }

    override fun showUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoginScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

