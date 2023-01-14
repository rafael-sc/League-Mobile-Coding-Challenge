package life.league.challenge.kotlin.data.local

import android.content.SharedPreferences

class AccessTokenDataSourceImpl(
    private val encryptedSharedPreferences: SharedPreferences
) : AccessTokenDataSource {
    override fun getAccessToken(): String? {
        return encryptedSharedPreferences.getString(KEY_ACCESS_TOKEN, null)
    }

    override fun setAccessToken(accessToken: String) {
        encryptedSharedPreferences.edit()
            .putString(KEY_ACCESS_TOKEN, accessToken)
            .apply()
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "_key_access_token"
    }
}
