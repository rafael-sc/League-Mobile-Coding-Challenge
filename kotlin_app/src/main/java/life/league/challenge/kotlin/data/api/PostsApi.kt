package life.league.challenge.kotlin.data.api

import life.league.challenge.kotlin.data.model.response.PostItemResponse
import retrofit2.http.GET

interface PostsApi {
    @GET("posts")
    suspend fun getPosts(): List<PostItemResponse>
}
