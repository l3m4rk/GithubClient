package example.l3m4rk.edu.githubclient.presentation.login.presenter

import example.l3m4rk.edu.githubclient.business.login.ILoginInteractor
import example.l3m4rk.edu.githubclient.data.app.Result
import example.l3m4rk.edu.githubclient.data.app.login.LoginAction
import example.l3m4rk.edu.githubclient.presentation.login.views.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter(val loginInteractor: ILoginInteractor) : ILoginPresenter {

    companion object {
        private const val TAG = "LoginPresenter"
    }

    private val disposables = CompositeDisposable()

    private var loginView: LoginView? = null

    override fun bindView(loginView: LoginView) {
        this.loginView = loginView
    }

    override fun login(login: String, password: String) {
        loginView?.showProgress()
        val d = loginInteractor.login(LoginAction(login, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result: Result ->
                    loginView?.hideProgress()
                    when (result) {
                        is Result.Success -> loginView?.showUserScreen()
                        else -> loginView?.showError()
                    }
                })
        disposables.add(d)

    }

    override fun unbindView() {
        disposables.dispose()
        loginView = null
    }


}