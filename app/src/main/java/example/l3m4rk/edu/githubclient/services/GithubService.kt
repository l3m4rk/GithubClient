package example.l3m4rk.edu.githubclient.services

import example.l3m4rk.edu.githubclient.data.network.commits.CommitDTO
import example.l3m4rk.edu.githubclient.data.network.login.UserDTO
import example.l3m4rk.edu.githubclient.data.network.repos.RepoDTO
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("user") fun getUser(): Single<UserDTO>

    @GET("user/repos") fun getRepos(): Observable<List<RepoDTO>>

    @GET("repos/{owner}/{repo}/commits")
    fun getCommits(@Path("owner") user: String, @Path("repo") repo: String): Observable<List<CommitDTO>>

}