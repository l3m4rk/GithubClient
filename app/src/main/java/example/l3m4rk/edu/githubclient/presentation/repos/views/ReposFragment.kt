package example.l3m4rk.edu.githubclient.presentation.repos.views

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.l3m4rk.edu.githubclient.R
import example.l3m4rk.edu.githubclient.presentation.repos.views.dummy.DummyContent
import example.l3m4rk.edu.githubclient.presentation.repos.views.dummy.DummyContent.DummyItem

class ReposFragment : Fragment() {

    private var mListener: OnReposInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_repos, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = ReposAdapter(DummyContent.ITEMS, mListener)
        }
        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnReposInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnReposInteractionListener {
        fun onRepoClicked(item: DummyItem)
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
