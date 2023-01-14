package life.league.challenge.kotlin.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import life.league.challenge.kotlin.data.LoginRepositoryImpl
import life.league.challenge.kotlin.data.api.LoginApi
import life.league.challenge.kotlin.data.local.AccessTokenDataSource
import life.league.challenge.kotlin.data.local.AccessTokenDataSourceImpl
import life.league.challenge.kotlin.domain.repository.LoginRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

object LoginModule {
    val instance = module {
        factory<LoginRepository> {
            LoginRepositoryImpl(
                loginApi = get(),
                accessTokenDataSource = get()
            )
        }
        factory<LoginApi> {
            provideLoginApi(
                get(qualifier = RetrofitQualifier)
            )
        }
        factory<AccessTokenDataSource> {
            AccessTokenDataSourceImpl(
                encryptedSharedPreferences = get()
            )
        }

        factory {
            provideEncryptedSharedPreferences(androidContext())
        }
    }

    private fun provideLoginApi(retrofit: Retrofit) = retrofit.create(LoginApi::class.java)

    // for reference https://proandroiddev.com/encrypted-preferences-in-android-af57a89af7c8
    private fun provideEncryptedSharedPreferences(context: Context): SharedPreferences =
        EncryptedSharedPreferences.create(
            "access_token_preferences",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
}
