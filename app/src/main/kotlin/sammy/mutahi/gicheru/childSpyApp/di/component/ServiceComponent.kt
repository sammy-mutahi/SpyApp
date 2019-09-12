package sammy.mutahi.gicheru.childSpyApp.di.component

import sammy.mutahi.gicheru.childSpyApp.di.PerService
import sammy.mutahi.gicheru.childSpyApp.di.module.ServiceModule
import sammy.mutahi.gicheru.childSpyApp.services.calls.CallsService
import sammy.mutahi.gicheru.childSpyApp.services.sms.SmsService
import sammy.mutahi.gicheru.childSpyApp.services.social.MonitorService
import dagger.Component

/**
 * Created by luis rafael on 13/03/18.
 */
@PerService
@Component(dependencies = [AppComponent::class], modules = [ServiceModule::class])
interface ServiceComponent {

    fun inject(callsService: CallsService)
    fun inject(smsService: SmsService)
    fun inject(monitorService: MonitorService)

}