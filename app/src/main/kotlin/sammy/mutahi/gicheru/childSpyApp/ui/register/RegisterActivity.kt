package sammy.mutahi.gicheru.childSpyApp.ui.register

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils.isEmpty
import android.util.Patterns
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.widget.RxTextView
import com.pawegio.kandroid.longToast
import com.pawegio.kandroid.startActivity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_register.*
import kotterknife.bindView
import sammy.mutahi.gicheru.childSpyApp.R
import sammy.mutahi.gicheru.childSpyApp.ui.base.BaseActivity
import sammy.mutahi.gicheru.childSpyApp.ui.login.LoginActivity
import sammy.mutahi.gicheru.childSpyApp.ui.login.LoginActivity_MembersInjector
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.startAndFinishActivity
import javax.inject.Inject

class RegisterActivity : BaseActivity() , InterfaceViewRegister {

    private val edtEmail: EditText by bindView(R.id.edit_register_email)
    private val edtPass: EditText by bindView(R.id.edit_register_password)
    private val edtPassRepeat: EditText by bindView(R.id.edit_register_repeat_password)
    private val btnSignUp: Button by bindView(R.id.btn_register_sign_up)

    @Inject
    lateinit var interactor: InterfaceInteractorRegister<InterfaceViewRegister>

    private lateinit var emailObservable: Observable<Boolean>
    private lateinit var passObservable: Observable<Boolean>
    private lateinit var signInEnabled: Observable<Boolean>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        getComponent()!!.inject(this)
        interactor.onAttach(this)
        emailValidationObservable(edtEmail)
        passValidationObservable(edtPass)
        passValidationObservable(edtPassRepeat)
        signInValidationObservable(btnSignUp)
        onClickRegister()
    }


    override fun onDestroy() {
        super.onDestroy()
         interactor.onDetach()
        clearDisposable()
    }

    private fun onClickRegister() {
        btnSignUp.setOnClickListener {
            if (edtPass.text.toString() == edtPassRepeat.text.toString())
                 interactor.signUpDisposable(edtEmail.text.toString(), edtPass.text.toString())
            else {
                edtPassRepeat.text.clear()
                edtPass.text.clear()
                showError("Register Password Match")
            }
        }
    }

    override fun successResult(boolean: Boolean) {

        if (boolean) {
            showMessage("Registered Successfully!!")
            startAndFinishActivity<LoginActivity>()
        } else {
            showError("Failed To Sign Up Try Again Later")
        }
    }

    override fun failedResult(throwable: Throwable) {
        longToast("Cannot Sign Up ${throwable.message}")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) startAndFinishActivity<LoginActivity>()
        return super.onKeyDown(keyCode, event)
    }

    /** Email validation */
    fun emailValidationObservable(edtEmail: EditText) {
        emailObservable = RxTextView.textChanges(edtEmail).map { textEmail -> Patterns.EMAIL_ADDRESS.matcher(textEmail).matches() }
        emailObservable(edtEmail)
    }

    private fun emailObservable(edtEmail: EditText) {
        emailObservable.distinctUntilChanged().map { b -> if (b) R.drawable.ic_user else R.drawable.ic_user_alert }
                .subscribe { id -> edtEmail.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0) }
    }

    /** Password validation */
    fun passValidationObservable(edtPass: EditText) {
        passObservable = RxTextView.textChanges(edtPass).map { textPass -> textPass.length > 5 }
        passObservable(edtPass)
    }

    private fun passObservable(edtPass: EditText) {
        passObservable.distinctUntilChanged().map { b -> if (b) R.drawable.ic_lock_open else R.drawable.ic_lock }
                .subscribe { id -> edtPass.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0) }
    }

    /** Sign In observer */
    fun signInValidationObservable(btnSignIn: Button) {
        signInEnabled = Observable.combineLatest(emailObservable, passObservable, BiFunction { email, pass -> email && pass })
        signInEnableObservable(btnSignIn)
    }

    private fun signInEnableObservable(btnSignIn: Button) {
        signInEnabled.distinctUntilChanged().subscribe { enabled -> btnSignIn.isEnabled = enabled }
        signInEnabled.distinctUntilChanged()
                .map { b -> if (b) R.color.colorAccent else R.color.colorTextDisabled }
                .subscribe { color -> btnSignIn.backgroundTintList = ContextCompat.getColorStateList(this, color) }

    }

}
