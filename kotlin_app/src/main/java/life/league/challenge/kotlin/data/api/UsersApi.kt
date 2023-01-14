package life.league.challenge.kotlin.data.api

import life.league.challenge.kotlin.data.model.response.UserItemResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UsersApi {
    @GET("users")
    suspend fun getUsers(@Header("x-access-token") accessToken: String): List<UserItemResponse>
}
