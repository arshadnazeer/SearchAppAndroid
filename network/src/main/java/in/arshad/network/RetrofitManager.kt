package `in`.arshad.network

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {
    lateinit var retrofit: Retrofit

    fun initRetroFit(context: Context) {
        val httpClient = OkHttpClient.Builder().authenticator(AccessAuthenticator())
                .connectTimeout(25, TimeUnit.SECONDS)
                .readTimeout(25, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                .addInterceptor(ChuckInterceptor(context))
                .addInterceptor(NetworkMonitorInterceptor(context))
        retrofit = Retrofit.Builder()
                .baseUrl("https://api.imgur.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
    }

    fun <T> getService(clz: Class<T>): T {
        return retrofit.create(clz)
    }

}
