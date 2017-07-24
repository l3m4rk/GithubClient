package example.l3m4rk.edu.githubclient.presentation.repos.presenter

import example.l3m4rk.edu.githubclient.business.commits.validation.ErrorParser
import example.l3m4rk.edu.githubclient.data.network.repos.RepoDTO
import example.l3m4rk.edu.githubclient.presentation.repos.models.RepoItem
import example.l3m4rk.edu.githubclient.presentation.repos.views.ReposView
import example.l3m4rk.edu.githubclient.services.GithubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ReposPresenter(private val githubService: GithubService,
                     private val errorParser: ErrorParser) : IReposPresenter {

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
                .subscribe(this::handleSuccess, this::handleError)
        disposables.add(d)
    }

    private fun handleSuccess(repos: List<RepoItem>) {
        reposView?.hideProgress()
        if (repos.isEmpty()) {
            reposView?.showEmptyState()
        } else {
            reposView?.showRepos(repos)
        }
    }

    private fun handleError(it: Throwable) {
        reposView?.hideProgress()
        reposView?.showError(errorParser.parseError(it))
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
