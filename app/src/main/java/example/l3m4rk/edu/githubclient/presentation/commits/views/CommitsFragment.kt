package example.l3m4rk.edu.githubclient.presentation.commits.views

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
import example.l3m4rk.edu.githubclient.presentation.commits.models.CommitItem
import example.l3m4rk.edu.githubclient.presentation.commits.presenter.ICommitsPresenter
import kotlinx.android.synthetic.main.empty_repos.*
import kotlinx.android.synthetic.main.error.*
import kotlinx.android.synthetic.main.progress.*
import javax.inject.Inject

class CommitsFragment : Fragment(), CommitsView {

    private var mOwner: String? = null
    private var mRepo: String? = null

    @Inject lateinit var commitsPresenter: ICommitsPresenter

    private var mListener: OnCommitInteractionListener? = null
    private lateinit var commitsAdapter: CommitsAdapter
    private lateinit var commitsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mOwner = arguments.getString(ARG_OWNER)
            mRepo = arguments.getString(ARG_REPO)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_commits, container, false)

        activity.title = getString(R.string.title_commits)

        commitsAdapter = CommitsAdapter(mListener)
        setupCommitsList(view)

        val retryButton = view.findViewById(R.id.retryButton)
        retryButton.setOnClickListener { commitsPresenter.loadCommits(mOwner!!, mRepo!!) }

        return view
    }

    private fun setupCommitsList(view: View) {
        val context = view.context
        commitsList = view.findViewById(R.id.commitsList) as RecyclerView
        commitsList.layoutManager = LinearLayoutManager(context)
        commitsList.itemAnimator = DefaultItemAnimator()
        commitsList.adapter = commitsAdapter
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is OnCommitInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
        commitsPresenter.bindView(this)
    }

    override fun onStart() {
        super.onStart()
        commitsPresenter.loadCommits(mOwner!!, mRepo!!)
    }

    override fun showProgress() {
        emptyView.visibility = View.GONE
        errorView.visibility = View.GONE
        progressView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
    }

    override fun showEmptyState() {
        emptyView.visibility = View.VISIBLE
    }

    override fun showError(errorMessage: String) {
        errorView.visibility = View.VISIBLE
        val messageView = errorView.findViewById(R.id.errorMessage) as TextView
        messageView.text = errorMessage
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
        commitsPresenter.unbindView()
    }

    override fun showCommits(commits: List<CommitItem>) {
        commitsAdapter.update(commits)
    }

    interface OnCommitInteractionListener {
        fun onCommitClicked(item: CommitItem)
    }

    companion object {

        private const val ARG_OWNER = "owner"
        private const val ARG_REPO = "repo"

        fun newInstance(owner: String, repo: String): CommitsFragment {
            val fragment = CommitsFragment()
            val args = Bundle()
            args.putString(ARG_OWNER, owner)
            args.putString(ARG_REPO, repo)
            fragment.arguments = args
            return fragment
        }
    }
}
