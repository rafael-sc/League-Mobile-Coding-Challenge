package life.league.challenge.kotlin.api

import life.league.challenge.kotlin.model.AccountData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface LoginApi {
    @GET("login")
    suspend fun login(@Header("Authorization") credentials: String?): Response<AccountData>
}
