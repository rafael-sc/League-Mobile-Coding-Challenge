package life.league.challenge.kotlin.commom.exceptions

sealed class ApiException : Throwable() {
    class UnableToGetPostsException : ApiException()
    class UnableToGetUsersException : ApiException()
    class UnableToLoginException : ApiException()
}
