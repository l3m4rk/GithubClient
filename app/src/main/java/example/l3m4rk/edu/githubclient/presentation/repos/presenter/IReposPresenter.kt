package example.l3m4rk.edu.githubclient.presentation.repos.presenter

import example.l3m4rk.edu.githubclient.presentation.repos.views.ReposView

interface IReposPresenter {

    fun bindView(view: ReposView)

    fun loadRepos()

    fun unbindView()

}