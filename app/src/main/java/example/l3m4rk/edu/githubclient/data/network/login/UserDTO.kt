package example.l3m4rk.edu.githubclient.data.network.login

import com.google.gson.annotations.SerializedName

data class UserDTO(
        @SerializedName("id") val id: Long,
        @SerializedName("login") val login: String,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("email") val email: String?
)