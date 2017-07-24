package example.l3m4rk.edu.githubclient.business.commits.validation

import example.l3m4rk.edu.githubclient.data.network.commits.CommitDTO
import example.l3m4rk.edu.githubclient.presentation.commits.models.CommitItem
import java.text.SimpleDateFormat
import java.util.*

class ServerCommitsParser {

    companion object {
        private val SERVER_DATE_FORMATTER = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        private val APP_DATE_FORMATTER = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    }

    fun parseItems(commits: List<CommitDTO>): List<CommitItem> {
        return commits.map { (sha, commitInfo) ->
            CommitItem(
                    sha.take(7),
                    commitInfo.message,
                    commitInfo.author.name,
                    parseServerDate(commitInfo.author.date)
            )
        }
    }

    private fun parseServerDate(dtoDate: String): String {
        val date = SERVER_DATE_FORMATTER.parse(dtoDate)
        return APP_DATE_FORMATTER.format(date)
    }

}