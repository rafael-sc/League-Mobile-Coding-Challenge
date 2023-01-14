package life.league.challenge.kotlin.domain.repository

interface LoginRepository {
    fun isUserAuthenticated(): Boolean
    suspend fun login(username: String, password: String): String
}
