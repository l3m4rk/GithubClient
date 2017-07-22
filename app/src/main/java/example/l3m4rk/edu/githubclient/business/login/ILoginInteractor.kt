package example.l3m4rk.edu.githubclient.business.login

import example.l3m4rk.edu.githubclient.data.app.Result
import example.l3m4rk.edu.githubclient.data.app.login.LoginAction
import io.reactivex.Single

interface ILoginInteractor {

    fun login(loginAction: LoginAction): Single<Result>
    
}

