package example.l3m4rk.edu.githubclient.business.user

import example.l3m4rk.edu.githubclient.data.app.Result
import io.reactivex.Single

interface IUserInteractor {

    fun checkUserLoggedIn(): Single<Result>

}