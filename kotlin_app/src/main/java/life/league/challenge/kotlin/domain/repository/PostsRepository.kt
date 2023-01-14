package life.league.challenge.kotlin.domain.repository

import life.league.challenge.kotlin.domain.model.Post

interface PostsRepository {
    suspend fun getPosts(): List<Post>
}
