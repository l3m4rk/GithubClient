package example.l3m4rk.edu.githubclient.services.auth

import android.content.Context
import android.preference.PreferenceManager

class AuthHolder(context: Context) : IAuthHolder {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    override fun saveToken(token: String) {
        prefs.edit().putString("token", token).apply()
    }

    override fun loadToken(): String {
        return prefs.getString("token", "")
    }
}