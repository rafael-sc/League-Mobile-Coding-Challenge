package life.league.challenge.kotlin.domain.repository

interface PostsRepository {
    suspend fun getPosts(accessToken: String): Any
}