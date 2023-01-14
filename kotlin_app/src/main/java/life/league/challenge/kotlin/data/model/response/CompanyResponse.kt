package life.league.challenge.kotlin.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompanyResponse(
    @field:Json(name = "bs") val bs: String,
    @field:Json(name = "catchPhrase") val catchPhrase: String,
    @field:Json(name = "name") val name: String
)
