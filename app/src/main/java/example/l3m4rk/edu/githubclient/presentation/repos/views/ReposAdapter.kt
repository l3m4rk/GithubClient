package example.l3m4rk.edu.githubclient.presentation.repos.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import example.l3m4rk.edu.githubclient.R

import example.l3m4rk.edu.githubclient.presentation.repos.views.ReposFragment.OnReposInteractionListener
import example.l3m4rk.edu.githubclient.presentation.repos.views.dummy.DummyContent.DummyItem

class ReposAdapter(private val mValues: List<DummyItem>, private val mListener: OnReposInteractionListener?) : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(repoHolder: RepoViewHolder, position: Int) {
        repoHolder.mItem = mValues[position]
        repoHolder.mNameView.text = mValues[position].id
        repoHolder.mDescriptionView.text = mValues[position].content

        repoHolder.mView.setOnClickListener {
            mListener?.onRepoClicked(repoHolder.mItem as DummyItem)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class RepoViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mNameView: TextView
        val mDescriptionView: TextView
        var mItem: DummyItem? = null

        init {
            mNameView = mView.findViewById(R.id.name) as TextView
            mDescriptionView = mView.findViewById(R.id.description) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mDescriptionView.text + "'"
        }
    }
}
