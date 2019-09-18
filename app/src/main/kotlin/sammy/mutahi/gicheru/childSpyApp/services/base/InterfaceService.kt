package sammy.mutahi.gicheru.childSpyApp.services.base

import sammy.mutahi.gicheru.childSpyApp.di.component.ServiceComponent
import io.reactivex.disposables.Disposable


interface InterfaceService {

    fun getComponent(): ServiceComponent?

    fun addDisposable(disposable: Disposable)

    fun clearDisposable()

}