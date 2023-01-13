package life.league.challenge.kotlin.domain.usecase

import life.league.challenge.kotlin.domain.repository.PostsRepository

class PostsUseCaseImpl(
    private val postsRepository: PostsRepository
) : PostsUseCase {
    override suspend fun getPosts(accessToken: String): Any {
        return postsRepository.getPosts(accessToken)
    }
}
