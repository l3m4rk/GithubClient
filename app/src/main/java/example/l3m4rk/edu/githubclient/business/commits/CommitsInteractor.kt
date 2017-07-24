package example.l3m4rk.edu.githubclient.business.commits

import example.l3m4rk.edu.githubclient.business.commits.validation.ServerCommitsParser
import example.l3m4rk.edu.githubclient.presentation.commits.models.CommitItem
import example.l3m4rk.edu.githubclient.services.GithubService
import io.reactivex.Observable

class CommitsInteractor(private val githubService: GithubService,
                        private val serverCommitsParser: ServerCommitsParser) : ICommitsInteractor {

    override fun loadCommits(owner: String, repo: String): Observable<List<CommitItem>> {
        return githubService.getCommits(owner, repo)
                .map(serverCommitsParser::parseItems)
    }
}