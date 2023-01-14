package life.league.challenge.kotlin.domain.model

data class Post(
    val id: Int,
    val title: String,
    val content: String,
    val user: User
)
