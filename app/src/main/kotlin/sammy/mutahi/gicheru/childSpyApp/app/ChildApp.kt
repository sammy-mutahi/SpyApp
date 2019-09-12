package sammy.mutahi.gicheru.childSpyApp.app

import android.app.Application
import android.arch.lifecycle.LifecycleObserver
import sammy.mutahi.gicheru.childSpyApp.di.component.AppComponent
import sammy.mutahi.gicheru.childSpyApp.di.component.DaggerAppComponent
import sammy.mutahi.gicheru.childSpyApp.di.module.AppModule
import sammy.mutahi.gicheru.childSpyApp.di.module.FirebaseModule

/**
 * Created by luis rafael on 13/03/18.
 */
class ChildApp : Application(), LifecycleObserver {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .firebaseModule(FirebaseModule())
                .build()
        appComponent.inject(this)
    }


}

