package example.l3m4rk.edu.githubclient.presentation.commits.models

data class CommitItem(
        val sha: String,
        val message: String,
        val author: String,
        val date: String
)