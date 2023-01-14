package life.league.challenge.kotlin.domain.usecase.login

interface LoginUseCase {
    fun isUserAuthenticated(): Boolean
    suspend fun login(userName: String, password: String): String
}
