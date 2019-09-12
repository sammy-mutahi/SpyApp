package sammy.mutahi.gicheru.childSpyApp.services.calls

import sammy.mutahi.gicheru.childSpyApp.di.PerService
import sammy.mutahi.gicheru.childSpyApp.services.base.InterfaceInteractorService

/**
 * Created by luis rafael on 27/03/18.
 */
@PerService
interface InterfaceInteractorCalls<S : InterfaceServiceCalls> : InterfaceInteractorService<S> {

    fun startRecording(phoneNumber:String?)
    fun stopRecording()

}