package sammy.mutahi.gicheru.childSpyApp.ui.login

import sammy.mutahi.gicheru.childSpyApp.ui.base.InterfaceView

/**
 * Created by luis rafael on 9/03/18.
 */
interface InterfaceViewLogin : InterfaceView {

    fun successResult(boolean: Boolean)

    fun failedResult(throwable: Throwable)

}