package sammy.mutahi.gicheru.childSpyApp.ui.login

import sammy.mutahi.gicheru.childSpyApp.ui.base.InterfaceView



interface InterfaceViewLogin : InterfaceView {

    fun successResult(boolean: Boolean)

    fun failedResult(throwable: Throwable)

}