package example.l3m4rk.edu.githubclient.presentation.commits.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import example.l3m4rk.edu.githubclient.R
import example.l3m4rk.edu.githubclient.presentation.commits.models.CommitItem

import example.l3m4rk.edu.githubclient.presentation.commits.views.CommitsFragment.OnCommitInteractionListener

class CommitsAdapter(private val mListener: OnCommitInteractionListener?) : RecyclerView.Adapter<CommitsAdapter.ViewHolder>() {

    private val mItems = ArrayList<CommitItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_commit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mItems[position]
        holder.mShaView.text = mItems[position].sha
        holder.mMessageView.text = mItems[position].message
        holder.mAuthorView.text = mItems[position].author
        holder.mDateView.text = mItems[position].date

        holder.mView.setOnClickListener {
            mListener?.onCommitClicked(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun update(commits: List<CommitItem>) {
        mItems.clear()
        mItems.addAll(commits)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mShaView: TextView
        val mMessageView: TextView
        val mAuthorView: TextView
        val mDateView: TextView

        var mItem: CommitItem? = null

        init {
            mShaView = mView.findViewById(R.id.sha) as TextView
            mMessageView = mView.findViewById(R.id.message) as TextView
            mAuthorView = mView.findViewById(R.id.author) as TextView
            mDateView = mView.findViewById(R.id.date) as TextView
        }
        override fun toString(): String {
            return super.toString() + " '" + mMessageView.text + "'"
        }

    }
}
