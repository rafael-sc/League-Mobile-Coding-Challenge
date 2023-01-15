package life.league.challenge.kotlin.data

import life.league.challenge.kotlin.commom.exceptions.ApiException
import life.league.challenge.kotlin.data.api.LoginApi
import life.league.challenge.kotlin.data.local.AccessTokenDataSource
import life.league.challenge.kotlin.data.model.request.LoginBody
import life.league.challenge.kotlin.data.model.request.asBasicEncodedString
import life.league.challenge.kotlin.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginApi: LoginApi,
    private val accessTokenDataSource: AccessTokenDataSource
) : LoginRepository {

    override suspend fun isUserAuthenticated(): Boolean {
        return accessTokenDataSource.getAccessToken().isNullOrEmpty().not()
    }

    override suspend fun login(username: String, password: String): String {
        val token = accessTokenDataSource.getAccessToken()
        return token ?: run {
            val login = LoginBody(username, password).asBasicEncodedString()
            val accessToken =
                loginApi.login(login).body()?.apiKey ?: throw ApiException.UnableToLoginException()
            accessTokenDataSource.setAccessToken(accessToken)
            return accessToken
        }
    }
}
