package life.league.challenge.kotlin.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PostsApi {
    @GET("posts")
    suspend fun getPosts(@Header("x-access-token") accessToken: String): Response<Any>
}
