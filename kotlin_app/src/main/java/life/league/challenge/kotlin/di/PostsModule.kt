package life.league.challenge.kotlin.di

import life.league.challenge.kotlin.api.PostsApi
import life.league.challenge.kotlin.api.UsersApi
import life.league.challenge.kotlin.data.PostsRepositoryImpl
import life.league.challenge.kotlin.domain.repository.PostsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

object PostsModule {
    val instance = module {
        factory<PostsRepository> {
            PostsRepositoryImpl(
                postsApi = get(),
                usersApi = get()
            )
        }
        factory<PostsApi> {
            providePostsApi(
                get(qualifier = RetrofitQualifier)
            )
        }
        factory<UsersApi> {
            provideUsersApi(
                get(qualifier = RetrofitQualifier)
            )
        }
    }

    private fun providePostsApi(retrofit: Retrofit) = retrofit.create(PostsApi::class.java)
    private fun provideUsersApi(retrofit: Retrofit) = retrofit.create(UsersApi::class.java)
}
