package example.l3m4rk.edu.githubclient.data.network.commits

import com.google.gson.annotations.SerializedName

data class CommitInfoDTO(
        @SerializedName("message") val message: String,
        @SerializedName("author") val author: AuthorDTO
)