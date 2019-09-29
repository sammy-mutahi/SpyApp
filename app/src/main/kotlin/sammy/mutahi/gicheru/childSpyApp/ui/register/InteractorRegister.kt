package sammy.mutahi.gicheru.childSpyApp.ui.register

import android.content.Context
import android.support.v4.app.FragmentManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sammy.mutahi.gicheru.childSpyApp.R
import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.InterfaceFirebase
import sammy.mutahi.gicheru.childSpyApp.ui.base.BaseInteractor
import javax.inject.Inject

class InteractorRegister<V : InterfaceViewRegister> @Inject constructor(context: Context, firebase: InterfaceFirebase) : BaseInteractor<V>(context, firebase), InterfaceInteractorRegister<V>{

    override fun signUpDisposable(email: String, pass: String) {
        getView().addDisposable(firebase().signUp(email, pass)
                .map { authResult -> authResult.user != null }
                .doOnSubscribe { getView().showLoading("Registering User") }
                .doFinally { getView().hideLoading() }
                .subscribe({ getView().successResult(it) }, { getView().failedResult(it) }))
    }


}