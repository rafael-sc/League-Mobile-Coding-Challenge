package life.league.challenge.kotlin.domain.usecase.login

import life.league.challenge.kotlin.domain.repository.LoginRepository

class LoginUseCaseImpl(
    private val loginRepository: LoginRepository
) : LoginUseCase {

    override suspend fun isUserAuthenticated() = loginRepository.isUserAuthenticated()

    override suspend fun login(userName: String, password: String): String {
        return loginRepository.login(userName, password)
    }
}
