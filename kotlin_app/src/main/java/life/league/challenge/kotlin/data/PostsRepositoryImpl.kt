package life.league.challenge.kotlin.data

import life.league.challenge.kotlin.commom.exceptions.UnableToGetPostsException
import life.league.challenge.kotlin.commom.exceptions.UnableToGetUsersException
import life.league.challenge.kotlin.data.api.PostsApi
import life.league.challenge.kotlin.data.api.UsersApi
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.model.User
import life.league.challenge.kotlin.domain.repository.PostsRepository

class PostsRepositoryImpl(
    private val postsApi: PostsApi,
    private val usersApi: UsersApi
) : PostsRepository {
    override suspend fun getPosts(): List<Post> {
        val loadedUsers = usersApi.getUsers().map {
            User(
                id = it.id,
                name = it.name,
                avatarUrl = it.avatar
            )
        }
        if (loadedUsers.isEmpty()) throw UnableToGetUsersException()

        val postsList: MutableList<Post> = mutableListOf()
        postsApi.getPosts().map { postItem ->
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

        if (postsList.isEmpty()) throw UnableToGetPostsException()

        return postsList
    }
}
