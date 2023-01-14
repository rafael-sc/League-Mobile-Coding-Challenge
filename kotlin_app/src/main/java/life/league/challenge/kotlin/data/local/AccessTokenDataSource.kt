package life.league.challenge.kotlin.data.local

interface AccessTokenDataSource {
    fun getAccessToken(): String?
    fun setAccessToken(accessToken: String)
}
