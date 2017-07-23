package example.l3m4rk.edu.githubclient.presentation.commits.presenter

import example.l3m4rk.edu.githubclient.presentation.commits.views.CommitsView

interface ICommitsPresenter {

    fun bindView(view: CommitsView)
    fun loadCommits(owner: String, repo: String)
    fun unbindView()

}

