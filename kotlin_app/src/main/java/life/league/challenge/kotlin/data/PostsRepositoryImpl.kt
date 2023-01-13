package life.league.challenge.kotlin.data

import life.league.challenge.kotlin.api.PostsApi
import life.league.challenge.kotlin.domain.repository.PostsRepository

class PostsRepositoryImpl(
    private val postsApi: PostsApi,
) : PostsRepository {
    override suspend fun getPosts(accessToken: String): Any {
        return postsApi.getPosts(accessToken)
    }

}
