package sammy.mutahi.gicheru.childSpyApp.utils.hiddenCameraServiceUtils.config

import android.support.annotation.IntDef


class CameraImageFormat{

    init {
        throw RuntimeException("Cannot initialize CameraImageFormat.")
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(FORMAT_JPEG)
    annotation class SupportedImageFormat

    companion object {
        const val FORMAT_JPEG = 849
    }
}
