package life.league.challenge.kotlin.data

import life.league.challenge.kotlin.api.LoginApi
import life.league.challenge.kotlin.data.model.request.LoginBody
import life.league.challenge.kotlin.data.model.request.asBasicEncodedString
import life.league.challenge.kotlin.domain.exceptions.UnableToLoginException
import life.league.challenge.kotlin.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginApi: LoginApi
) : LoginRepository {

    override suspend fun login(username: String, password: String): String {
        val login = LoginBody(username, password).asBasicEncodedString()
        return loginApi.login(login).body()?.apiKey ?: throw UnableToLoginException()
    }
}
