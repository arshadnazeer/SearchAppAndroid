package `in`.arshad.network.retrofit

import `in`.arshad.domain.CloudBaseResponse
import `in`.arshad.domain.ErrorBaseResponse
import `in`.arshad.network.RetrofitManager
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

abstract class BaseCallBacks<T : CloudBaseResponse> : Callback<T> {

    abstract fun failure(error: ErrorBaseResponse, rawResponseBody: ResponseBody?)

    abstract fun success(successResponse: T)
    abstract fun networkOrUnexpectedError(error: ErrorBaseResponse)

    /*An HTTP response may still indicate an application-level failure such as a 404 or 500.*/
    override fun onResponse(call: Call<T>, response: Response<T>) {

        if (response.isSuccessful && response.body() != null) {
            success(response.body()!!)
        } else {
            sendFailureResponse(response)
        }
    }

    private fun sendFailureResponse(response: Response<T>) {
        try {
            if (response.errorBody() != null) {
                failure(parseError(response), response.errorBody())
            } else {
                networkOrUnexpectedError(errorBaseResponse())
            }
        } catch (e: Exception) {
            networkOrUnexpectedError(errorBaseResponse())
        }
    }

    /*Invoked when a network exception occurred talking to the server or when an unexpected
    exception occurred creating the request or processing the response.*/
    override fun onFailure(call: Call<T>, t: Throwable) {
        networkOrUnexpectedError(errorBaseResponse())
    }

    private fun parseError(response: Response<*>): ErrorBaseResponse {
        val converter = RetrofitManager.retrofit
                .responseBodyConverter<ErrorBaseResponse>(ErrorBaseResponse::class.java, arrayOfNulls<Annotation>(0))

        val error: ErrorBaseResponse?

        error = try {
            converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            Log.e("error", "" + e.toString())
            errorBaseResponse()
        }

        if (error == null) {
            return errorBaseResponse()
        }

        return error
    }

    private fun errorBaseResponse(): ErrorBaseResponse {
        val error1 = ErrorBaseResponse()
        error1.code = "901"
        error1.msg = "Please check your internet connection"
        return error1
    }
}