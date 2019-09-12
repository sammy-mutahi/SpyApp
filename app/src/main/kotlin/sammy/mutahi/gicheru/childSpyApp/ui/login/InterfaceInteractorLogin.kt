package sammy.mutahi.gicheru.childSpyApp.ui.login

import sammy.mutahi.gicheru.childSpyApp.di.PerActivity
import sammy.mutahi.gicheru.childSpyApp.ui.base.InterfaceInteractor

/**
 * Created by luis rafael on 9/03/18.
 */
@PerActivity
interface InterfaceInteractorLogin<V : InterfaceViewLogin> : InterfaceInteractor<V> {
    fun signInDisposable(email: String, pass: String)
}