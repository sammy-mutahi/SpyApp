package sammy.mutahi.gicheru.childSpyApp.services.sms

import sammy.mutahi.gicheru.childSpyApp.di.PerService
import sammy.mutahi.gicheru.childSpyApp.services.base.InterfaceInteractorService

/**
 * Created by luis rafael on 27/03/18.
 */
@PerService
interface InterfaceInteractorSms<S : InterfaceServiceSms> : InterfaceInteractorService<S> {

    fun setPushSms(smsAddress: String, smsBody: String)

}