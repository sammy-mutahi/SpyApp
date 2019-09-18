package sammy.mutahi.gicheru.childSpyApp.data.rxFirebase

import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.RxTask.Companion.assignOnTask
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Maybe


object RxFirebaseAuth {
    fun FirebaseAuth.rxSignInWithEmailAndPassword(email: String, password: String): Maybe<AuthResult> =
            Maybe.create { emiter -> assignOnTask(emiter, signInWithEmailAndPassword(email, password)) }
}