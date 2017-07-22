package example.l3m4rk.edu.githubclient.presentation.login.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import dagger.android.AndroidInjection
import example.l3m4rk.edu.githubclient.R
import example.l3m4rk.edu.githubclient.presentation.login.presenter.ILoginPresenter
import example.l3m4rk.edu.githubclient.presentation.user.views.UserActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import kotlinx.android.synthetic.main.progress.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginView {

    @Inject lateinit var loginPresenter: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        title = getString(R.string.title_activity_login)

        loginButton.setOnClickListener {
            loginPresenter.login(loginInput.text.toString(), passwordInput.text.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        loginPresenter.bindView(this)
    }

    override fun showProgress() {
        progressView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
    }

    override fun showError() {
        Toast.makeText(this, "Что-то пошло не так!", Toast.LENGTH_SHORT).show()
    }

    override fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun showUserScreen() {
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }

    override fun onStop() {
        super.onStop()
        loginPresenter.unbindView()
    }

}
