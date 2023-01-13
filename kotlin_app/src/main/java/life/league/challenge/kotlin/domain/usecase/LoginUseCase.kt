package life.league.challenge.kotlin.domain.usecase

interface LoginUseCase {
    suspend fun login(userName: String, password: String): String
}