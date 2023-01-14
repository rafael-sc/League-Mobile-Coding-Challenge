package life.league.challenge.kotlin.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressResponse(
    @field:Json(name = "city") val city: String,
    @field:Json(name = "geo") val geo: GeoResponse,
    @field:Json(name = "street") val street: String,
    @field:Json(name = "suite") val suite: String,
    @field:Json(name = "zipcode") val zipcode: String
)
