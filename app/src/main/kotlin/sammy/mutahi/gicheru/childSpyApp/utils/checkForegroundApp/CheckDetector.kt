package sammy.mutahi.gicheru.childSpyApp.utils.checkForegroundApp

import android.content.Context


interface CheckDetector {

    fun getForegroundApp(context: Context): String?

}