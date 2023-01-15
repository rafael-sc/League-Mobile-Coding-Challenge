package life.league.challenge.kotlin.data

import life.league.challenge.kotlin.commom.exceptions.ApiException
import life.league.challenge.kotlin.data.api.PostsApi
import life.league.challenge.kotlin.data.api.UsersApi
import life.league.challenge.kotlin.data.extensions.toDomain
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.repository.PostsRepository

class PostsRepositoryImpl(
    private val postsApi: PostsApi,
    private val usersApi: UsersApi
) : PostsRepository {
    override suspend fun getPosts(): List<Post> {
        val loadedUsers = usersApi.getUsers().map {
            it.toDomain()
        }
        if (loadedUsers.isEmpty()) throw ApiException.UnableToGetUsersException()

        val postsList: MutableList<Post> = mutableListOf()
        postsApi.getPosts().map { postItem ->
            val user = loadedUsers.firstOrNull { it.id == postItem.userId }
            if (user != null) {
                postsList.add(
                    postItem.toDomain(user)
                )
            }
        }

        if (postsList.isEmpty()) throw ApiException.UnableToGetPostsException()

        return postsList
    }
}
