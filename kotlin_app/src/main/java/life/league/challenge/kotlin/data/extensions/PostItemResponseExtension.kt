package life.league.challenge.kotlin.data.extensions

import life.league.challenge.kotlin.data.model.response.PostItemResponse
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.model.User

fun PostItemResponse.toDomain(user: User) = Post(
    id = id,
    title = title,
    content = body.replace("\n", " "),
    user = user
)
