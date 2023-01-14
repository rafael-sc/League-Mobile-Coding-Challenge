package life.league.challenge.kotlin.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostItemResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "userId") val userId: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "body") val body: String
)
