package sammy.mutahi.gicheru.childSpyApp.utils.checkForegroundApp

import android.annotation.TargetApi
import android.app.Service
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import sammy.mutahi.gicheru.childSpyApp.utils.checkForegroundApp.CheckPermission.hasUsageStatsPermission


class CheckForegroundApp : CheckDetector {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun getForegroundApp(context: Context): String? {

        if (!context.hasUsageStatsPermission()) return null

        var foregroundApp: String? = null

        val mUsageStatsManager = context.getSystemService(Service.USAGE_STATS_SERVICE) as UsageStatsManager
        val time = System.currentTimeMillis()

        val usageEvents = mUsageStatsManager.queryEvents(time - 1000 * 3600, time)
        val event = UsageEvents.Event()
        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event)
            if (event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                foregroundApp = event.packageName
            }
        }

        return foregroundApp
    }

}