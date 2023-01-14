package life.league.challenge.kotlin.domain.usecase.posts

import life.league.challenge.kotlin.commom.exceptions.UnableToLoginException
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.repository.LoginRepository
import life.league.challenge.kotlin.domain.repository.PostsRepository

class PostsUseCaseImpl(
    private val loginRepository: LoginRepository,
    private val postsRepository: PostsRepository
) : PostsUseCase {
    override suspend fun getPosts(): List<Post> {
        if (loginRepository.isUserAuthenticated()) {
            return postsRepository.getPosts()
        } else {
            throw UnableToLoginException()
        }
    }
}
