package life.league.challenge.kotlin

import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockkStatic
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import life.league.challenge.kotlin.data.LoginRepositoryImpl
import life.league.challenge.kotlin.data.api.LoginApi
import life.league.challenge.kotlin.data.local.AccessTokenDataSource
import life.league.challenge.kotlin.data.model.response.LoginResponse
import life.league.challenge.kotlin.domain.repository.LoginRepository
import life.league.challenge.kotlin.domain.usecase.login.LoginUseCase
import life.league.challenge.kotlin.domain.usecase.login.LoginUseCaseImpl
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var loginRepository: LoginRepository

    @MockK
    private lateinit var accessTokenDataSource: AccessTokenDataSource

    @MockK
    private lateinit var loginApi: LoginApi

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        loginRepository =
            LoginRepositoryImpl(loginApi = loginApi, accessTokenDataSource = accessTokenDataSource)
        loginUseCase = LoginUseCaseImpl(loginRepository)
    }

    @Test
    fun `isUserAuthenticated() - user is authenticated - should return true`() = runTest {
        // arrange
        coEvery { accessTokenDataSource.getAccessToken() } returns ACCESS_TOKEN_MOCK

        // act
        val result = loginUseCase.isUserAuthenticated()

        // assert
        result shouldBe true
    }

    @Before
    fun mockBase64Encoding() {
        mockkStatic(android.util.Base64::class)
        every { android.util.Base64.encodeToString(any(), any()) } returns "random"
    }

    @Test
    fun `login() - user is not authenticated - should save api key received in loginApi login`() =
        runTest {
            // arrange
            coEvery { loginApi.login(any()) } returns Response.success(
                LoginResponse(
                    apiKey = ACCESS_TOKEN_MOCK
                )
            )
            coEvery { accessTokenDataSource.getAccessToken() } returns null
            coEvery { accessTokenDataSource.setAccessToken(any()) } just runs
            // act
            loginUseCase.login("user", "password")

            // assert
            coVerify {
                accessTokenDataSource.setAccessToken(ACCESS_TOKEN_MOCK)
                accessTokenDataSource.getAccessToken()
            }
        }

    companion object {
        const val ACCESS_TOKEN_MOCK = "mocked access token"
    }
}
