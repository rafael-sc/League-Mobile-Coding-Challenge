package life.league.challenge.kotlin.domain.usecase.posts

import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.repository.PostsRepository

class PostsUseCaseImpl(
    private val postsRepository: PostsRepository
) : PostsUseCase {
    override suspend fun getPosts(): List<Post> {
        return postsRepository.getPosts()
    }
}
