package example.l3m4rk.edu.githubclient.presentation.repos.presenter

import example.l3m4rk.edu.githubclient.data.network.repos.RepoDTO
import example.l3m4rk.edu.githubclient.presentation.repos.models.RepoItem
import example.l3m4rk.edu.githubclient.presentation.repos.views.ReposView
import example.l3m4rk.edu.githubclient.services.GithubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ReposPresenter(private val githubService: GithubService) : IReposPresenter {

    private var reposView: ReposView? = null
    private val disposables = CompositeDisposable()

    override fun bindView(view: ReposView) {
        reposView = view
    }

    override fun loadRepos() {
        reposView?.showProgress()
        val d = githubService.getRepos()
                .map { repoDtoList: List<RepoDTO> -> map(repoDtoList) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    repos: List<RepoItem> ->
                    reposView?.hideProgress()
                    if (repos.isEmpty()) {
                        reposView?.showEmptyState()
                    } else {
                        reposView?.showRepos(repos)
                    }
                }, {
                    reposView?.hideProgress()
                    reposView?.showError()
                })
        disposables.add(d)
    }

    fun map(repoDtoList: List<RepoDTO>): List<RepoItem> {
        return repoDtoList.map {
            repoDTO -> RepoItem(
                repoDTO.owner.login,
                repoDTO.owner.avatarUrl,
                repoDTO.name,
                repoDTO.description ?: "-" ,
                repoDTO.forks.toString(),
                repoDTO.watchers.toString())
        }
    }

    override fun unbindView() {
        disposables.clear()
        reposView = null
    }
}
