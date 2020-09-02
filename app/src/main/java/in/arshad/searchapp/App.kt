package `in`.arshad.searchapp

import `in`.arshad.network.RetrofitManager
import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        RetrofitManager.initRetroFit(this)
    }

    companion object {
        lateinit var instance: App
    }
}
