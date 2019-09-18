package sammy.mutahi.gicheru.childSpyApp.ui.login

import sammy.mutahi.gicheru.childSpyApp.di.PerActivity
import sammy.mutahi.gicheru.childSpyApp.ui.base.InterfaceInteractor


@PerActivity
interface InterfaceInteractorLogin<V : InterfaceViewLogin> : InterfaceInteractor<V> {
    fun signInDisposable(email: String, pass: String)
}