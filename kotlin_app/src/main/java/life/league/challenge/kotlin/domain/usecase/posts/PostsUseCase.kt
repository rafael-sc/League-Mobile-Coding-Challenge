package life.league.challenge.kotlin.domain.usecase.posts

import life.league.challenge.kotlin.domain.model.Post

interface PostsUseCase {
    suspend fun getPosts(accessToken: String): List<Post>
}
