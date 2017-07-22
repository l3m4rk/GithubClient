package example.l3m4rk.edu.githubclient.business.user

import example.l3m4rk.edu.githubclient.data.app.Result
import example.l3m4rk.edu.githubclient.data.app.user.User
import example.l3m4rk.edu.githubclient.repositories.user.IUserRepository
import example.l3m4rk.edu.githubclient.services.auth.IAuthHolder
import io.reactivex.Single

class UserInteractor(private val userRepository: IUserRepository,
                     private val authHolder: IAuthHolder) : IUserInteractor {

    override fun checkUserLoggedIn(): Single<Result> {
        return userRepository.loadUser()
                .flatMap { user: User ->
                    if (authHolder.loadToken().isNotEmpty() &&
                            user.login.isNotEmpty() &&
                            user.password.isNotEmpty() &&
                            user.avatarUrl.isNotEmpty() &&
                            user.authToken.isNotEmpty()) {
                        return@flatMap Single.just(Result.Success())
                    }
                    return@flatMap Single.just(Result.Fail(Exception()))
                }
    }
}