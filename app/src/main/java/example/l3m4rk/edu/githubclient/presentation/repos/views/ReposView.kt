package example.l3m4rk.edu.githubclient.presentation.repos.views

import example.l3m4rk.edu.githubclient.presentation.repos.models.RepoItem

interface ReposView {

    fun showProgress()
    fun hideProgress()
    fun showRepos(repos: List<RepoItem>)
    fun showEmptyState()
    fun showError()

}