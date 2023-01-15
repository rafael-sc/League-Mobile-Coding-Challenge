package life.league.challenge.kotlin.data.extensions

import life.league.challenge.kotlin.data.model.response.UserItemResponse
import life.league.challenge.kotlin.domain.model.User

fun UserItemResponse.toDomain() = User(
    id = id,
    name = name,
    avatarUrl = avatar
)
