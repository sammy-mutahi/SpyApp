package sammy.mutahi.gicheru.childSpyApp.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.pawegio.kandroid.startActivity
import kotlinx.android.synthetic.main.activity_generate_qr_code.*
import sammy.mutahi.gicheru.childSpyApp.R
import sammy.mutahi.gicheru.childSpyApp.ui.dashboard.helper.EncryptionHelper
import sammy.mutahi.gicheru.childSpyApp.ui.dashboard.helper.QRCodeHelper
import sammy.mutahi.gicheru.childSpyApp.ui.dashboard.models.UserObjec
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.getDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GenerateQrCodeActivity : AppCompatActivity() {

    companion object {

        fun getGenerateQrCodeActivity(callingClassContext: Context) = Intent(callingClassContext, GenerateQrCodeActivity::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_qr_code)
        localDateTime.setText(getDateTime())
        generateQrCodeButton.setOnClickListener {
            if (checkEditText()) {
                hideKeyboard()
                val user = UserObjec(name = fullNameEditText.text.toString(), date_time =  getDateTime())
                FirebaseDatabase.getInstance().getReference("qrcode").child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(user)

                val serializeString = Gson().toJson(user)
                val encryptedString = EncryptionHelper.getInstance().encryptionString(serializeString).encryptMsg()
                setImageBitmap(encryptedString)
                goToMainDashBoard()
            }
        }
    }

    private fun goToMainDashBoard() {
        startActivity<MainDashBoard>()
    }

    private fun setImageBitmap(encryptedString: String?) {
        val bitmap = QRCodeHelper.newInstance(this).setContent(encryptedString).setErrorCorrectionLevel(ErrorCorrectionLevel.Q).setMargin(2).qrcOde
        qrCodeImageView.setImageBitmap(bitmap)
    }

    /**
     * Hides the soft input keyboard if it is shown to the screen.
     */

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun checkEditText(): Boolean {
        if (TextUtils.isEmpty(fullNameEditText.text.toString())) {
            Toast.makeText(this, "fullName field cannot be empty!", Toast.LENGTH_SHORT).show()
            return false
        } else if (TextUtils.isEmpty(localDateTime.text.toString())) {
            Toast.makeText(this, "DateTime linkedfield cannot be empty!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
