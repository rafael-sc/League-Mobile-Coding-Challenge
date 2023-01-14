package life.league.challenge.kotlin.api

import life.league.challenge.kotlin.data.model.response.PostItemResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface PostsApi {
    @GET("posts")
    suspend fun getPosts(@Header("x-access-token") accessToken: String): List<PostItemResponse>
}
