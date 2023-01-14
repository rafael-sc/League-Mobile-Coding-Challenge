package life.league.challenge.kotlin.data

import android.util.Log
import life.league.challenge.kotlin.api.PostsApi
import life.league.challenge.kotlin.api.UsersApi
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.repository.PostsRepository

class PostsRepositoryImpl(
    private val postsApi: PostsApi,
    private val usersApi: UsersApi
) : PostsRepository {
    override suspend fun getPosts(accessToken: String): List<Post> {
        usersApi.getUsers(accessToken).map {
            Log.d("User - ${it.id}", it.name)
        }

        return postsApi.getPosts(accessToken).map {
            Post(
                userId = it.userId,
                id = it.id,
                title = it.title,
                content = it.body.replace("\n", " ")
            )
        }
    }
}
