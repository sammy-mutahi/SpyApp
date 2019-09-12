package sammy.mutahi.gicheru.childSpyApp.services.base

import android.content.Context
import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.InterfaceFirebase
import com.google.firebase.auth.FirebaseUser

/**
 * Created by luis rafael on 22/03/18.
 */
interface InterfaceInteractorService<S : InterfaceService> {

    fun onAttach(service: S)

    fun onDetach()

    fun getService(): S?

    fun getContext(): Context

    fun firebase(): InterfaceFirebase

    fun user(): FirebaseUser?

}