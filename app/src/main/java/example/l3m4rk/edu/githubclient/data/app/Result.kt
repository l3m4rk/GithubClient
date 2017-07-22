package example.l3m4rk.edu.githubclient.data.app

sealed class Result {
    class Success : Result()
    class Fail(ex: Exception): Result()
    class Loading(): Result()
}