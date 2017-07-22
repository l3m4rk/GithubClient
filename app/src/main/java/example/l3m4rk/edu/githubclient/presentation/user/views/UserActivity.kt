package example.l3m4rk.edu.githubclient.presentation.user.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import dagger.android.AndroidInjection
import example.l3m4rk.edu.githubclient.R
import example.l3m4rk.edu.githubclient.data.app.user.User
import example.l3m4rk.edu.githubclient.presentation.login.views.LoginActivity
import example.l3m4rk.edu.githubclient.presentation.user.presenter.IUserPresenter
import javax.inject.Inject

class UserActivity : AppCompatActivity(), UserView {

    @Inject lateinit var userPresenter: IUserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }

    override fun onStart() {
        super.onStart()
        userPresenter.bindView(this)
        userPresenter.checkUserLoggedIn()
    }

    override fun showUser(user: User) {
        Log.i("USER", "userPresenter = ${userPresenter}")
    }

    override fun showLoginScreen() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onStop() {
        super.onStop()
        userPresenter.unbindView()
    }
}

