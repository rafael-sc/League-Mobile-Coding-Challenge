package life.league.challenge.kotlin.data.api

import life.league.challenge.kotlin.data.model.response.UserItemResponse
import retrofit2.http.GET

interface UsersApi {
    @GET("users")
    suspend fun getUsers(): List<UserItemResponse>
}
