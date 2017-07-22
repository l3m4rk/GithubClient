package example.l3m4rk.edu.githubclient.business.user

import example.l3m4rk.edu.githubclient.data.app.Result
import example.l3m4rk.edu.githubclient.data.app.user.User
import example.l3m4rk.edu.githubclient.repositories.user.IUserRepository
import io.reactivex.Single

class UserInteractor(private val userRepository: IUserRepository) : IUserInteractor {

    override fun checkUserLoggedIn(): Single<Result> {
        return userRepository.loadUser().map { user: User -> Result.Success() }
    }
}