package example.l3m4rk.edu.githubclient.data.network.commits

import com.google.gson.annotations.SerializedName

data class AuthorDTO(
        @SerializedName("name") val name: String,
        @SerializedName("email") val email: String,
        @SerializedName("date") val date: String
)