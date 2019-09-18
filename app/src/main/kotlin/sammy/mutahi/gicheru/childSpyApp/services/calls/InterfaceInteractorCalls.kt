package sammy.mutahi.gicheru.childSpyApp.services.calls

import sammy.mutahi.gicheru.childSpyApp.di.PerService
import sammy.mutahi.gicheru.childSpyApp.services.base.InterfaceInteractorService

@PerService
interface InterfaceInteractorCalls<S : InterfaceServiceCalls> : InterfaceInteractorService<S> {

    fun startRecording(phoneNumber:String?)
    fun stopRecording()

}