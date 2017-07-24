package example.l3m4rk.edu.githubclient.business.commits.validation

import android.content.Context
import example.l3m4rk.edu.githubclient.R
import java.io.IOException

class ErrorParser(private val context: Context) {

    fun parseError(t: Throwable): String {
        when (t) {
            is IOException -> return context.getString(R.string.error_no_connection)
            else -> return context.getString(R.string.error_common)
        }
    }
}