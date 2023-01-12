package life.league.challenge.kotlin.domain.repository

interface LoginRepository {
    suspend fun login(username: String, password: String): String?
}