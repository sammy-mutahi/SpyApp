package sammy.mutahi.gicheru.childSpyApp.ui.login

import android.content.Context
import sammy.mutahi.gicheru.childSpyApp.R
import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.InterfaceFirebase
import sammy.mutahi.gicheru.childSpyApp.ui.base.BaseInteractor
import javax.inject.Inject

/**
 * Created by luis rafael on 9/03/18.
 */
class InteractorLogin<V : InterfaceViewLogin> @Inject constructor(context: Context, firebase: InterfaceFirebase) : BaseInteractor<V>(context, firebase), InterfaceInteractorLogin<V> {

    override fun signInDisposable(email: String, pass: String) {
        getView().addDisposable(firebase().signIn(email, pass)
                .map { authResult -> authResult.user != null }
                .doOnSubscribe { getView().showLoading(getContext().getString(R.string.logging_in)) }
                .doFinally { getView().hideLoading() }
                .subscribe({ getView().successResult(it) }, { getView().failedResult(it) }))
    }
}