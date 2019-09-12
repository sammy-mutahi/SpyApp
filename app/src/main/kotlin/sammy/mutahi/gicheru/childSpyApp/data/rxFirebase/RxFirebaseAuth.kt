package sammy.mutahi.gicheru.childSpyApp.data.rxFirebase

import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.RxTask.Companion.assignOnTask
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Maybe

/**
 * Created by luis rafael on 13/03/18.
 */
object RxFirebaseAuth {
    fun FirebaseAuth.rxSignInWithEmailAndPassword(email: String, password: String): Maybe<AuthResult> =
            Maybe.create { emitter -> assignOnTask(emitter, signInWithEmailAndPassword(email, password)) }
}