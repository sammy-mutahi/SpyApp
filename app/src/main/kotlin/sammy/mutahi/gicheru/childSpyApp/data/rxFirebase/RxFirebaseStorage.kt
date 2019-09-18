package sammy.mutahi.gicheru.childSpyApp.data.rxFirebase

import android.net.Uri
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import io.reactivex.Single


object RxFirebaseStorage {

    fun StorageReference.rxPutFile(uri: Uri): Single<UploadTask.TaskSnapshot> {
        return Single.create<UploadTask.TaskSnapshot> { emitter ->
            val taskSnapshotStorageTask = putFile(uri)
                    .addOnSuccessListener { taskSnapshot -> emitter.onSuccess(taskSnapshot) }
                    .addOnFailureListener { error ->
                        if (!emitter.isDisposed) {
                            emitter.onError(error)
                        }
                    }
            emitter.setCancellable { taskSnapshotStorageTask.cancel() }
        }
    }

}