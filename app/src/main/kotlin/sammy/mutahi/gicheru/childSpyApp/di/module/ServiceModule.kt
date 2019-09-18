package sammy.mutahi.gicheru.childSpyApp.di.module

import android.app.Service
import android.content.Context
import sammy.mutahi.gicheru.childSpyApp.di.PerService
import sammy.mutahi.gicheru.childSpyApp.services.calls.InteractorCalls
import sammy.mutahi.gicheru.childSpyApp.services.calls.InterfaceInteractorCalls
import sammy.mutahi.gicheru.childSpyApp.services.calls.InterfaceServiceCalls
import sammy.mutahi.gicheru.childSpyApp.services.sms.InteractorSms
import sammy.mutahi.gicheru.childSpyApp.services.sms.InterfaceInteractorSms
import sammy.mutahi.gicheru.childSpyApp.services.sms.InterfaceServiceSms
import dagger.Module
import dagger.Provides


@Module
class ServiceModule(var service:Service) {

    @Provides
    fun provideContext(): Context = service.applicationContext

    @Provides
    @PerService
    fun provideInterfaceInteractorCalls(interactor: InteractorCalls<InterfaceServiceCalls>): InterfaceInteractorCalls<InterfaceServiceCalls> = interactor

    @Provides
    @PerService
    fun provideInterfaceInteractorSms(interactor: InteractorSms<InterfaceServiceSms>): InterfaceInteractorSms<InterfaceServiceSms> = interactor

}