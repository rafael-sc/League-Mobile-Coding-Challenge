package life.league.challenge.kotlin.di

import life.league.challenge.kotlin.api.LoginApi
import life.league.challenge.kotlin.data.LoginRepositoryImpl
import life.league.challenge.kotlin.domain.repository.LoginRepository
import org.koin.dsl.module
import retrofit2.Retrofit

object LoginModule {
    val instance = module {
        factory<LoginRepository> {
            LoginRepositoryImpl(
                loginApi = get()
            )
        }
        factory<LoginApi> {
            provideLoginApi(
                get(qualifier = RetrofitQualifier)
            )
        }
    }

    private fun provideLoginApi(retrofit: Retrofit) = retrofit.create(LoginApi::class.java)
}