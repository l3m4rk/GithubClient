package example.l3m4rk.edu.githubclient.presentation.repos.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import example.l3m4rk.edu.githubclient.R
import example.l3m4rk.edu.githubclient.presentation.repos.models.RepoItem
import example.l3m4rk.edu.githubclient.presentation.repos.views.ReposFragment.OnReposInteractionListener

class ReposAdapter(private val mListener: OnReposInteractionListener?) : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    private val mItems = ArrayList<RepoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(repoHolder: RepoViewHolder, position: Int) {
        val repoItem = mItems[position]
        repoHolder.mItem = repoItem
        repoHolder.mAuthorView.text = repoItem.author

        Picasso.with(repoHolder.itemView.context)
                .load(repoItem.avatarUrl)
//                .centerCrop()
                .into(repoHolder.mAvatarView)

        repoHolder.mNameView.text = repoItem.name
        repoHolder.mDescriptionView.text = repoItem.description
        repoHolder.mForksView.text = repoItem.forksCount
        repoHolder.mWatchesView.text = repoItem.watchCount

        repoHolder.mView.setOnClickListener {
            mListener?.onRepoClicked(repoItem)
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun update(repos: List<RepoItem>) {
        mItems.clear()
        mItems.addAll(repos)
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mAuthorView: TextView
        val mAvatarView: ImageView
        val mNameView: TextView
        val mDescriptionView: TextView
        val mForksView: TextView
        val mWatchesView: TextView

        var mItem: RepoItem? = null

        init {
            mAvatarView = mView.findViewById(R.id.avatar) as ImageView
            mAuthorView = mView.findViewById(R.id.author) as TextView
            mNameView = mView.findViewById(R.id.name) as TextView
            mDescriptionView = mView.findViewById(R.id.description) as TextView
            mForksView = mView.findViewById(R.id.forks) as TextView
            mWatchesView = mView.findViewById(R.id.watches) as TextView
        }
        override fun toString(): String {
            return super.toString() + " '" + mDescriptionView.text + "'"
        }

    }
}
