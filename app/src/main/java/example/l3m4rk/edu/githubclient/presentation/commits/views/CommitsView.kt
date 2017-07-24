package example.l3m4rk.edu.githubclient.presentation.commits.views

import example.l3m4rk.edu.githubclient.presentation.commits.models.CommitItem

interface CommitsView {

    fun showProgress()
    fun hideProgress()
    fun showCommits(commits: List<CommitItem>)
    fun showEmptyState()
    fun showError(errorMessage: String)

}