package example.l3m4rk.edu.githubclient.repositories.user

import example.l3m4rk.edu.githubclient.data.app.Result
import example.l3m4rk.edu.githubclient.data.app.user.User
import io.reactivex.Single

interface IUserRepository {

    fun saveUser(user: User): Result

    fun loadUser(): Single<User>

}