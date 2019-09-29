package sammy.mutahi.gicheru.childSpyApp.ui.fragments

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.pawegio.kandroid.longToast
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.dialog_resetpassword.*
import kotterknife.bindView
import sammy.mutahi.gicheru.childSpyApp.R

class PasswordReset:DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dialog_resetpassword, container, false)
        val confirm = view.findViewById<TextView>(R.id.dialogConfirm)

        confirm.setOnClickListener {
            if(!isEmpty(email_password_reset.text.toString())){
                sendPasswordResetEmail(email_password_reset.text.toString())
                dialog.dismiss()
            }else{
                longToast("Email Field Is Required!!")
            }

        }
        return view
    }

    fun sendPasswordResetEmail(email: String) {
       FirebaseAuth.getInstance().sendPasswordResetEmail(email)
               .addOnCompleteListener({
                   if(it.isSuccessful()){
                       longToast("Check Your Email Password Has been sent")

                   }else{
                       longToast("Something Went Wrong!!")
                   }
               })

    }

}