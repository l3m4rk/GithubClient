package example.l3m4rk.edu.githubclient.business.commits

import example.l3m4rk.edu.githubclient.presentation.commits.models.CommitItem
import io.reactivex.Observable

interface ICommitsInteractor {

    fun loadCommits(owner: String, repo: String): Observable<List<CommitItem>>

}