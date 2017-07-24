package example.l3m4rk.edu.githubclient.presentation.commits.presenter

import example.l3m4rk.edu.githubclient.business.commits.ICommitsInteractor
import example.l3m4rk.edu.githubclient.business.commits.validation.ErrorParser
import example.l3m4rk.edu.githubclient.presentation.commits.models.CommitItem
import example.l3m4rk.edu.githubclient.presentation.commits.views.CommitsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CommitsPresenter(private val commitsInteractor: ICommitsInteractor,
                       private val errorParser: ErrorParser) : ICommitsPresenter {

    companion object {
        private const val TAG = "CommitsPresenter"
    }

    private var commitsView: CommitsView? = null
    private var disposables = CompositeDisposable()

    override fun bindView(view: CommitsView) {
        commitsView = view
    }

    override fun loadCommits(owner: String, repo: String) {
        commitsView?.showProgress()
        val d = commitsInteractor.loadCommits(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError)
        disposables.add(d)
    }

    private fun handleSuccess(commits: List<CommitItem>) {
        commitsView?.hideProgress()
        if (commits.isEmpty()) {
            commitsView?.showEmptyState()
        } else {
            commitsView?.showCommits(commits)
        }
    }

    private fun handleError(t: Throwable) {
        commitsView?.hideProgress()
        commitsView?.showError(errorParser.parseError(t))
    }

    override fun unbindView() {
        disposables.clear()
        commitsView = null
    }
}

