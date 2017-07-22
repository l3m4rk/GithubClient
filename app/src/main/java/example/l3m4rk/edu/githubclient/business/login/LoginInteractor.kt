package example.l3m4rk.edu.githubclient.business.login

import example.l3m4rk.edu.githubclient.data.app.Result
import example.l3m4rk.edu.githubclient.data.app.error.LoginException
import example.l3m4rk.edu.githubclient.data.app.login.LoginAction
import example.l3m4rk.edu.githubclient.data.app.user.User
import example.l3m4rk.edu.githubclient.data.network.login.UserDTO
import example.l3m4rk.edu.githubclient.repositories.user.IUserRepository
import example.l3m4rk.edu.githubclient.services.GithubService
import io.reactivex.Single
import okhttp3.Credentials

class LoginInteractor(private val githubService: GithubService,
                      private val userRepository: IUserRepository) : ILoginInteractor {

    override fun login(loginAction: LoginAction): Single<Result> {

        val basicToken = Credentials.basic(loginAction.login, loginAction.password)

        return githubService.getUser()
                .map { userDto: UserDTO -> User(loginAction.login, loginAction.password, userDto.avatarUrl, basicToken) }
                .map { user -> userRepository.saveUser(user) }
                .onErrorReturn { Result.Fail(LoginException()) }
    }

}

