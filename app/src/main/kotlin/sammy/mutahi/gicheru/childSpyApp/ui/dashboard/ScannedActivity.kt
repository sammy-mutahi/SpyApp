package sammy.mutahi.gicheru.childSpyApp.ui.dashboard

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_scanned.*
import sammy.mutahi.gicheru.childSpyApp.R
import sammy.mutahi.gicheru.childSpyApp.ui.dashboard.helper.EncryptionHelper
import sammy.mutahi.gicheru.childSpyApp.ui.dashboard.models.UserObjec

class ScannedActivity : AppCompatActivity() {

    companion object {
        private const val SCANNED_STRING: String = "scanned_string"
        fun getScannedActivity(callingClassContext: Context, encryptedString: String): Intent {
            return Intent(callingClassContext, ScannedActivity::class.java)
                    .putExtra(SCANNED_STRING, encryptedString)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanned)
        if (intent.getSerializableExtra(SCANNED_STRING) == null)
            throw RuntimeException("No encrypted String found in intent")
        val decryptedString = EncryptionHelper.getInstance().getDecryptionString(intent.getStringExtra(SCANNED_STRING))
        val userObject = Gson().fromJson(decryptedString, UserObjec::class.java)
        scannedFullNameTextView.text = userObject.name.toString()
        scannedAgeTextView.text = userObject.date_time.toString()
    }
}
