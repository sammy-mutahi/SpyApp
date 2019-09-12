package sammy.mutahi.gicheru.childSpyApp.services.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import sammy.mutahi.gicheru.childSpyApp.app.ChildApp
import sammy.mutahi.gicheru.childSpyApp.di.component.ServiceComponent
import sammy.mutahi.gicheru.childSpyApp.di.module.ServiceModule
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import sammy.mutahi.gicheru.childSpyApp.di.component.DaggerServiceComponent

/**
 * Created by luis rafael on 13/03/18.
 */
abstract class BaseService : Service(), InterfaceService {


    private lateinit var compositeDisposable: CompositeDisposable

    companion object {
        @JvmStatic
        lateinit var serviceComponent: ServiceComponent
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        serviceComponent = DaggerServiceComponent.builder().serviceModule(ServiceModule(this))
                .appComponent(ChildApp.appComponent).build()
        compositeDisposable = CompositeDisposable()
    }

    override fun getComponent(): ServiceComponent? = serviceComponent


    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun clearDisposable() = compositeDisposable.clear()


}