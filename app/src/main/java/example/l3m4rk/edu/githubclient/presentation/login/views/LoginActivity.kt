package example.l3m4rk.edu.githubclient.presentation.login.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import dagger.android.AndroidInjection
import example.l3m4rk.edu.githubclient.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        loginButton.setOnClickListener {

        }

    }

}
