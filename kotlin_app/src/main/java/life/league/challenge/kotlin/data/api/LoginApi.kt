package life.league.challenge.kotlin.data.api

import life.league.challenge.kotlin.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface LoginApi {
    @GET("login")
    suspend fun login(@Header("Authorization") credentials: String?): Response<LoginResponse>
}
