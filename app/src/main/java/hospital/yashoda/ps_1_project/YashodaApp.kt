package hospital.yashoda.ps_1_project

import android.app.Application


class YashodaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance : YashodaApp
            private set
    }
}