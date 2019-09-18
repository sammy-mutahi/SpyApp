package sammy.mutahi.gicheru.childSpyApp.di.component

import sammy.mutahi.gicheru.childSpyApp.app.ChildApp
import sammy.mutahi.gicheru.childSpyApp.data.rxFirebase.InterfaceFirebase
import sammy.mutahi.gicheru.childSpyApp.di.module.AppModule
import sammy.mutahi.gicheru.childSpyApp.di.module.FirebaseModule
import sammy.mutahi.gicheru.childSpyApp.services.accessibilityData.AccessibilityDataService
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, FirebaseModule::class])
interface AppComponent {

    fun inject(app: ChildApp)
    fun inject(accessibilityDataService: AccessibilityDataService)
    fun getInterfaceFirebase(): InterfaceFirebase

}