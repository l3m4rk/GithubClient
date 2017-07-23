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
import example.l3m4rk.edu.githubclient.R
import example.l3m4rk.edu.githubclient.presentation.commits.models.CommitItem
import example.l3m4rk.edu.githubclient.presentation.commits.views.dummy.DummyContent
import example.l3m4rk.edu.githubclient.presentation.commits.views.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.empty.*
import kotlinx.android.synthetic.main.error.*
import kotlinx.android.synthetic.main.progress.*

class CommitsFragment : Fragment(), CommitsView {
    // TODO: Customize parameters
    private var mColumnCount = 1

    private var mListener: OnCommitInteractionListener? = null
    private lateinit var commitsAdapter: CommitsAdapter
    private lateinit var commitsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_commits, container, false)

        activity.title = getString(R.string.title_commits)

        commitsAdapter = CommitsAdapter(DummyContent.ITEMS, mListener)
        setupCommitsList(view)

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
        super.onAttach(context)
        if (context is OnCommitInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
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

    override fun showError() {
        errorView.visibility = View.VISIBLE
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun showCommits(commits: List<CommitItem>) {
        commitsAdapter.update(commits)
    }

    interface OnCommitInteractionListener {
        // TODO: Update argument type and name
        fun onCommitClicked(item: DummyItem)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): CommitsFragment {
            val fragment = CommitsFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
