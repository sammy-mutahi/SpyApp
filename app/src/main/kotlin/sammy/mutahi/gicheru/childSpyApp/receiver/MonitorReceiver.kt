package sammy.mutahi.gicheru.childSpyApp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import sammy.mutahi.gicheru.childSpyApp.services.social.MonitorService
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.RESTART_MONITOR_RECEIVER
import com.pawegio.kandroid.IntentFor


class MonitorReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == RESTART_MONITOR_RECEIVER){
            context.startService(IntentFor<MonitorService>(context))
        }
    }

}