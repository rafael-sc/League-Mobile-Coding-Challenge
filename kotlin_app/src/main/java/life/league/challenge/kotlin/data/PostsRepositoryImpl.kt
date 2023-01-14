package life.league.challenge.kotlin.data

import life.league.challenge.kotlin.api.PostsApi
import life.league.challenge.kotlin.api.UsersApi
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.model.User
import life.league.challenge.kotlin.domain.repository.PostsRepository

class PostsRepositoryImpl(
    private val postsApi: PostsApi,
    private val usersApi: UsersApi
) : PostsRepository {
    override suspend fun getPosts(accessToken: String): List<Post> {
        val loadedUsers = usersApi.getUsers(accessToken).map {
            User(
                id = it.id,
                name = it.name,
                avatarUrl = it.avatar
            )
        }

        val postsList: MutableList<Post> = mutableListOf()
        postsApi.getPosts(accessToken).map { postItem ->
            val user = loadedUsers.firstOrNull { it.id == postItem.userId }
            if (user != null) {
                postsList.add(
                    Post(
                        id = postItem.id,
                        title = postItem.title,
                        content = postItem.body.replace("\n", " "),
                        user = user
                    )
                )
            }
        }
        return postsList
    }
}
