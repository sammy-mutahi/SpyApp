package sammy.mutahi.gicheru.childSpyApp.ui.base

import android.content.Context
import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.InterfaceFirebase
import com.google.firebase.auth.FirebaseUser


interface InterfaceInteractor<V : InterfaceView> {

    fun onAttach(view: V)

    fun onDetach()

    fun getView(): V

    fun getContext(): Context

    fun firebase(): InterfaceFirebase

    fun user(): FirebaseUser?

}