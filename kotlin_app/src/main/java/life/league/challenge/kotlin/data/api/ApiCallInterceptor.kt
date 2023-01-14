package life.league.challenge.kotlin.data.api

import life.league.challenge.kotlin.data.local.AccessTokenDataSource
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiCallInterceptor(
    private val accessTokenDataSource: AccessTokenDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = try {
        chain.proceed(
            chain.request().newBuilder().addHeader(
                name = "x-access-token",
                value = accessTokenDataSource.getAccessToken().orEmpty()
            ).build()
        )
    } catch (e: IOException) {
        throw e
    }
}
