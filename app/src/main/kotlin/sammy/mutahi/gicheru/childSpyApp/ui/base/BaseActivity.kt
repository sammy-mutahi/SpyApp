package sammy.mutahi.gicheru.childSpyApp.ui.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sammy.mutahi.gicheru.childSpyApp.app.ChildApp
import sammy.mutahi.gicheru.childSpyApp.di.component.ActivityComponent
import sammy.mutahi.gicheru.childSpyApp.di.module.ActivityModule
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.alertDialog
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.openAppSystemSettings
import com.pawegio.kandroid.indeterminateProgressDialog
import com.pawegio.kandroid.longToast
import com.tbruyelle.rxpermissions2.Permission
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import sammy.mutahi.gicheru.childSpyApp.R
import sammy.mutahi.gicheru.childSpyApp.di.component.DaggerActivityComponent

/**
 * Created by luis rafael on 13/03/18.
 */
abstract class BaseActivity : AppCompatActivity(), InterfaceView {


    private var progressDialog: ProgressDialog? = null
    private lateinit var compositeDisposable: CompositeDisposable

    companion object {
        @JvmStatic
        lateinit var activityComponent: ActivityComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(this))
                .appComponent(ChildApp.appComponent)
                .build()
        compositeDisposable = CompositeDisposable()

    }

    override fun getComponent(): ActivityComponent? = activityComponent

    override fun subscribePermission(permission: Permission, msgRationale: Int, msgDenied: Int, granted: () -> Unit) {
        when {
            permission.granted -> granted()
            permission.shouldShowRequestPermissionRationale -> showAlertDialog(msgRationale, android.R.string.ok, false) { dismiss() }
            else -> showAlertDialog(msgDenied, R.string.go_to_setting) { openAppSystemSettings() }
        }
    }

    override fun showAlertDialog(message: Int, txtPositiveButton: Int, cancelable: Boolean, func: DialogInterface.() -> Unit) {
        alertDialog(message, txtPositiveButton, cancelable, func)
    }

    override fun showLoading(msg: String) {
        progressDialog = indeterminateProgressDialog(msg) { setCancelable(false) }
    }

    override fun hideLoading() {
        if (progressDialog != null) progressDialog!!.dismiss()
    }

    override fun showError(message: String) {
        showMessage(message)
    }

    override fun showMessage(message: String) {
        longToast(message)
    }

    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun clearDisposable() = compositeDisposable.clear()
}