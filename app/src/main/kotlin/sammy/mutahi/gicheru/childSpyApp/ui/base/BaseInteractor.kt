package sammy.mutahi.gicheru.childSpyApp.ui.base

import android.content.Context
import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.InterfaceFirebase
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

open class BaseInteractor<V : InterfaceView> @Inject constructor(private var context: Context, private var firebase: InterfaceFirebase) : InterfaceInteractor<V> {

    private var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

    override fun getView(): V = view!!

    override fun getContext(): Context = context

    override fun firebase(): InterfaceFirebase = firebase

    override fun user(): FirebaseUser? = firebase.getUser()


}