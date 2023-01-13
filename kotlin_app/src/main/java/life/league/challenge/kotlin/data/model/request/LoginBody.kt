package life.league.challenge.kotlin.data.model.request

data class LoginBody(val username: String, val password: String)

fun LoginBody.asBasicEncodedString(): String =
    "Basic " + android.util.Base64.encodeToString(
        "$username:$password".toByteArray(),
        android.util.Base64.NO_WRAP
    )
