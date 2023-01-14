package life.league.challenge.kotlin.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeoResponse(
    @field:Json(name = "lat") val lat: String,
    @field:Json(name = "lng") val lng: String
)
