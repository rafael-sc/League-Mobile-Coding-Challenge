package life.league.challenge.kotlin.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountData(
    @field:Json(name = "api_key") val apiKey: String? = null
)
