package example.l3m4rk.edu.githubclient.repositories.user

import android.content.Context
import android.preference.PreferenceManager
import example.l3m4rk.edu.githubclient.data.app.Result
import example.l3m4rk.edu.githubclient.data.app.user.User
import io.reactivex.Single

class UserRepository(context: Context) : IUserRepository {

    companion object {
        private const val KEY_LOGIN = "key_login"
        private const val KEY_PASSWORD = "key_password"
        private const val KEY_AVATAR_URL = "avatar_url"
        private const val KEY_TOKEN = "key_password"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun saveUser(user: User): Result {
        preferences.edit().apply {
            putString(KEY_LOGIN, user.login)
            putString(KEY_PASSWORD, user.password)
            putString(KEY_AVATAR_URL, user.avatarUrl)
//            putString(KEY_TOKEN, user.authToken)
        }.apply()
        return Result.Success()
    }

    override fun loadUser(): Single<User> {
        val login = preferences.getString(KEY_LOGIN, "")
        val password = preferences.getString(KEY_PASSWORD, "")
        val avatarUrl = preferences.getString(KEY_AVATAR_URL, "")
        val token = preferences.getString(KEY_TOKEN, "")
        return Single.just(User(login, password, avatarUrl, token))
    }

}
