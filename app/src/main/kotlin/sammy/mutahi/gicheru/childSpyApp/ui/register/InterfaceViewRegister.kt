package sammy.mutahi.gicheru.childSpyApp.ui.register

import sammy.mutahi.gicheru.childSpyApp.ui.base.InterfaceView

interface InterfaceViewRegister : InterfaceView{
    fun successResult(boolean: Boolean)

    fun failedResult(throwable: Throwable)
}