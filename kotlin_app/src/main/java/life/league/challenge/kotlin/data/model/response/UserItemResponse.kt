package life.league.challenge.kotlin.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserItemResponse(
    @field:Json(name = "address") val address: AddressResponse,
    @field:Json(name = "avatar") val avatar: String,
    @field:Json(name = "company") val company: CompanyResponse,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "phone") val phone: String,
    @field:Json(name = "username") val username: String,
    @field:Json(name = "website") val website: String
)
