package life.league.challenge.kotlin.data

import life.league.challenge.kotlin.api.PostsApi
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.repository.PostsRepository

class PostsRepositoryImpl(
    private val postsApi: PostsApi
) : PostsRepository {
    override suspend fun getPosts(accessToken: String): List<Post> {
        return postsApi.getPosts(accessToken).map {
            Post(
                userId = it.userId,
                id = it.id,
                title = it.title,
                content = it.body.replace("\n", " ")
            )
        }
    }
}
