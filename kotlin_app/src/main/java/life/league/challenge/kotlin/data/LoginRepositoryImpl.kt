package life.league.challenge.kotlin.data

import life.league.challenge.kotlin.api.LoginApi
import life.league.challenge.kotlin.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginApi: LoginApi,
) : LoginRepository {

    override suspend fun login(username: String, password: String): String? {
        val login = LoginBody(username, password).asBasicEncodedString()
        val result = loginApi.login(login)
        return result.body()?.apiKey
    }

    data class LoginBody(val username: String, val password: String)

    private fun LoginBody.asBasicEncodedString(): String =
        "Basic " + android.util.Base64.encodeToString(
            "$username:$password".toByteArray(),
            android.util.Base64.NO_WRAP
        )
}
