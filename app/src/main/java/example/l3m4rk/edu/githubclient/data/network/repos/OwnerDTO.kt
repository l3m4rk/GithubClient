package example.l3m4rk.edu.githubclient.data.network.repos

import com.google.gson.annotations.SerializedName

data class OwnerDTO(
        @SerializedName("login") val login: String,
        @SerializedName("avatar_url") val avatarUrl: String
)
