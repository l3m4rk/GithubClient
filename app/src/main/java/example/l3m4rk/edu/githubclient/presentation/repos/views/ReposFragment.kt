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
import dagger.android.support.AndroidSupportInjection
import example.l3m4rk.edu.githubclient.R
import example.l3m4rk.edu.githubclient.presentation.repos.models.RepoItem
import example.l3m4rk.edu.githubclient.presentation.repos.presenter.IReposPresenter
import javax.inject.Inject

class ReposFragment : Fragment(), ReposView {
    private var mListener: OnReposInteractionListener? = null

    @Inject lateinit var reposPresenter: IReposPresenter

    private lateinit var reposAdapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_repos, container, false)

        reposAdapter = ReposAdapter(mListener)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.adapter = reposAdapter
        }
        return view
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmptyState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
