package life.league.challenge.kotlin

import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import life.league.challenge.kotlin.commom.exceptions.ApiException
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.model.User
import life.league.challenge.kotlin.domain.repository.LoginRepository
import life.league.challenge.kotlin.domain.repository.PostsRepository
import life.league.challenge.kotlin.domain.usecase.posts.PostsUseCase
import life.league.challenge.kotlin.domain.usecase.posts.PostsUseCaseImpl
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PostsUseCaseTest {

    private lateinit var postsUseCase: PostsUseCase

    @MockK
    private lateinit var postsRepository: PostsRepository

    @MockK
    private lateinit var loginRepository: LoginRepository

    private val mockedPostsList = listOf(
        Post(
            id = 0,
            title = "title",
            content = "content",
            user = User(
                id = 0,
                name = "User 0",
                avatarUrl = "http://avatar.url/0.jpg"
            )
        ),
        Post(
            id = 1,
            title = "title",
            content = "content",
            user = User(
                id = 1,
                name = "User 1",
                avatarUrl = "http://avatar.url/1.jpg"
            )
        )
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        postsUseCase = PostsUseCaseImpl(loginRepository, postsRepository)
    }

    @Test
    fun `getPosts() - user is authenticated - should return mocked Posts`() = runTest {
        // arrange
        coEvery { loginRepository.isUserAuthenticated() } returns true
        coEvery { postsRepository.getPosts() } returns mockedPostsList

        // act
        val receivedPosts = postsUseCase.getPosts()

        // assert
        receivedPosts shouldBe mockedPostsList
    }

    @Test(expected = ApiException.UnableToLoginException::class)
    fun `getPosts() - user is not allowed to login - should throw UnableToLoginException`() =
        runTest {
            // arrange
            coEvery { loginRepository.isUserAuthenticated() } returns false
            coEvery {
                loginRepository.login(
                    any(),
                    any()
                )
            } throws ApiException.UnableToLoginException()

            // act
            postsUseCase.getPosts()

            // assert
            // test should throw UnableToLoginException
        }
}
