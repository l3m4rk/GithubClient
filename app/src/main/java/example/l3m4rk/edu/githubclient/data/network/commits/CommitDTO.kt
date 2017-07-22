package example.l3m4rk.edu.githubclient.data.network.commits

import com.google.gson.annotations.SerializedName

data class CommitDTO(
        @SerializedName("sha") val sha: String,
        @SerializedName("commit") val commitInfo: CommitInfoDTO
)

