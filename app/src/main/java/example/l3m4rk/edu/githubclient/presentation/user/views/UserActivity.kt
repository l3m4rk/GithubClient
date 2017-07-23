package example.l3m4rk.edu.githubclient.presentation.user.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import example.l3m4rk.edu.githubclient.R
import example.l3m4rk.edu.githubclient.data.app.user.User
import example.l3m4rk.edu.githubclient.presentation.login.views.LoginActivity
import example.l3m4rk.edu.githubclient.presentation.repos.models.RepoItem
import example.l3m4rk.edu.githubclient.presentation.repos.views.ReposFragment
import example.l3m4rk.edu.githubclient.presentation.user.presenter.IUserPresenter
import kotlinx.android.synthetic.main.activity_user.*
import javax.inject.Inject

class UserActivity : AppCompatActivity(), UserView, ReposFragment.OnReposInteractionListener, HasSupportFragmentInjector {

    companion object {
        private const val TAG = "UserActivity"
    }

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject lateinit var userPresenter: IUserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setSupportActionBar(toolbar)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onStart() {
        super.onStart()
        userPresenter.bindView(this)
        userPresenter.checkUserLoggedIn()
    }

    override fun showRepos(user: User) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ReposFragment.newInstance())
                .commit()
    }

    override fun onRepoClicked(item: RepoItem) {
        Log.i(TAG, item.toString())
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

