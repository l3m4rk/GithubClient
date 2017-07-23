package example.l3m4rk.edu.githubclient.presentation.repos.models

data class RepoItem(
        val author: String,
        val avatarUrl: String,
        val name: String,
        val description: String,
        val forksCount: String,
        val watchCount: String
)