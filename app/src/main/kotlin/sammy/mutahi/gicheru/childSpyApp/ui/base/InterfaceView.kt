package sammy.mutahi.gicheru.childSpyApp.ui.base

import android.content.DialogInterface
import sammy.mutahi.gicheru.childSpyApp.di.component.ActivityComponent
import com.tbruyelle.rxpermissions2.Permission
import io.reactivex.disposables.Disposable


interface InterfaceView {

    fun getComponent(): ActivityComponent?

    fun subscribePermission(permission: Permission, msgRationale: Int, msgDenied: Int, granted: () -> Unit)

    fun showAlertDialog(message: Int, txtPositiveButton: Int, cancelable: Boolean = true, func: DialogInterface.() -> Unit)

    fun showLoading(msg: String)

    fun hideLoading()

    fun showError(message: String)

    fun showMessage(message: String)

    fun addDisposable(disposable: Disposable)

    fun clearDisposable()

}