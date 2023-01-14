package life.league.challenge.kotlin.domain.model

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val content: String
)
