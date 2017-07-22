package example.l3m4rk.edu.githubclient.services.auth

interface IAuthHolder {

    fun saveToken(token: String)

    fun loadToken(): String

}