package example.l3m4rk.edu.githubclient.presentation.commits.presenter

import example.l3m4rk.edu.githubclient.business.commits.validation.ErrorParser
import example.l3m4rk.edu.githubclient.data.network.commits.CommitDTO
import example.l3m4rk.edu.githubclient.presentation.commits.models.CommitItem
import example.l3m4rk.edu.githubclient.presentation.commits.views.CommitsView
import example.l3m4rk.edu.githubclient.services.GithubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class CommitsPresenter(private val githubService: GithubService, private val errorParser: ErrorParser) : ICommitsPresenter {

    companion object {
        private const val TAG = "CommitsPresenter"
    }

    private var commitsView: CommitsView? = null
    private val disposables = CompositeDisposable()

    override fun bindView(view: CommitsView) {
        commitsView = view
    }

    override fun loadCommits(owner: String, repo: String) {
        commitsView?.showProgress()
        val d = githubService.getCommits(owner, repo)
                .map { commits: List<CommitDTO> -> createItems(commits) }
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

    private fun createItems(commits: List<CommitDTO>): List<CommitItem> {
        return commits.map { (sha, commitInfo) -> CommitItem(sha.take(7), commitInfo.message, commitInfo.author.name, parseCommitDate(commitInfo.author.date)) }
    }

    private fun parseCommitDate(dtoDate: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(dtoDate)
        return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)
    }

    override fun unbindView() {
        disposables.clear()
        commitsView = null
    }
}

