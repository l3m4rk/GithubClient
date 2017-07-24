package example.l3m4rk.edu.githubclient.presentation.repos.views

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dagger.android.support.AndroidSupportInjection
import example.l3m4rk.edu.githubclient.R
import example.l3m4rk.edu.githubclient.presentation.repos.models.RepoItem
import example.l3m4rk.edu.githubclient.presentation.repos.presenter.IReposPresenter
import kotlinx.android.synthetic.main.empty_repos.*
import kotlinx.android.synthetic.main.error.*
import kotlinx.android.synthetic.main.progress.*
import javax.inject.Inject

class ReposFragment : Fragment(), ReposView {
    private var mListener: OnReposInteractionListener? = null

    @Inject lateinit var reposPresenter: IReposPresenter

    private lateinit var reposAdapter: ReposAdapter
    private lateinit var repoList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_repos, container, false)

        activity.title = getString(R.string.title_repos)

        reposAdapter = ReposAdapter(mListener)
        setupRepoList(view)

        val retryButton = view.findViewById(R.id.retryButton)
        retryButton.setOnClickListener { reposPresenter.loadRepos() }
        return view
    }

    private fun setupRepoList(view: View) {
        val context = view.context
        repoList = view.findViewById(R.id.reposList) as RecyclerView
        repoList.layoutManager = LinearLayoutManager(context)
        repoList.itemAnimator = DefaultItemAnimator()
        repoList.adapter = reposAdapter
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is OnReposInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
        reposPresenter.bindView(this)
    }

    override fun onStart() {
        super.onStart()
        reposPresenter.loadRepos()
    }

    override fun showProgress() {
        emptyView.visibility = View.GONE
        errorView.visibility = View.GONE
        progressView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
    }

    override fun showError(errorMessage: String) {
        errorView.visibility = View.VISIBLE
        val messageView = errorView.findViewById(R.id.errorMessage) as TextView
        messageView.text = errorMessage
    }

    override fun showEmptyState() {
        emptyView.visibility = View.VISIBLE
    }

    override fun showRepos(repos: List<RepoItem>) {
        reposAdapter.update(repos)
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
        reposPresenter.unbindView()
    }

    interface OnReposInteractionListener {
        fun onRepoClicked(item: RepoItem)

    }

    companion object {

        fun newInstance(): ReposFragment {
            val fragment = ReposFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
