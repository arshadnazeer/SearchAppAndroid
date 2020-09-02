package `in`.arshad.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

const val AUTHORIZATION = "Authorization"

class AccessAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request()
                .newBuilder()
                .addHeader(AUTHORIZATION, "Client-ID 137cda6b5008a7c")
                .build()
    }
}
