package life.league.challenge.kotlin.domain.usecase

interface PostsUseCase {
    suspend fun getPosts(): Any
}