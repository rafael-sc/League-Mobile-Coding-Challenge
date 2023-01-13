package life.league.challenge.kotlin.di

import life.league.challenge.kotlin.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitModule {
    private const val BASE_API_URL = "https://engineering.league.dev/challenge/api/"

    val instance: Module = module {
        single(RetrofitQualifier) {
            provideRetrofit(
                okHttpClientBuilder = get(OkHttpClientBuilderQualifier)
            )
        }

        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
        single(OkHttpClientBuilderQualifier) {
            provideOkHttpClientBuilderQualifier(httpLoggingInterceptor)
        }
    }

    private fun provideRetrofit(okHttpClientBuilder: OkHttpClient.Builder) =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    private fun provideOkHttpClientBuilderQualifier(vararg interceptors: Interceptor) =
        OkHttpClient.Builder().let { client ->
            interceptors.forEach { interceptor ->
                client.addInterceptor(interceptor)
            }
            client.run {
                connectTimeout(50, TimeUnit.SECONDS)
                readTimeout(50, TimeUnit.SECONDS)
                writeTimeout(50, TimeUnit.SECONDS)
            }
        }
}

object RetrofitQualifier : Qualifier {
    override val value: QualifierValue
        get() = "RetrofitQualifier"
}

object OkHttpClientBuilderQualifier : Qualifier {
    override val value: QualifierValue
        get() = "OkHttpClientBuilderQualifier"
}
