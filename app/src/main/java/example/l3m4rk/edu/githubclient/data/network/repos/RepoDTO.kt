package example.l3m4rk.edu.githubclient.data.network.repos

import com.google.gson.annotations.SerializedName


data class RepoDTO(
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String?,
        @SerializedName("owner") val owner: OwnerDTO,
        @SerializedName("forks") val forks: Int,
        @SerializedName("watchers") val watchers: Int
)