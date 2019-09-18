package sammy.mutahi.gicheru.childSpyApp.utils.checkForegroundApp

import android.app.AppOpsManager
import android.content.Context


object CheckPermission{
    fun Context.hasUsageStatsPermission(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow("android:get_usage_stats",
                android.os.Process.myUid(), packageName)
        return mode == AppOpsManager.MODE_ALLOWED
    }
}