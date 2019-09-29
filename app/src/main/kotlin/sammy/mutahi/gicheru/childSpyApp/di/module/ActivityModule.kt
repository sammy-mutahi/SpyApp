package sammy.mutahi.gicheru.childSpyApp.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import sammy.mutahi.gicheru.childSpyApp.di.PerActivity
import sammy.mutahi.gicheru.childSpyApp.ui.login.InteractorLogin
import sammy.mutahi.gicheru.childSpyApp.ui.login.InterfaceInteractorLogin
import sammy.mutahi.gicheru.childSpyApp.ui.login.InterfaceViewLogin
import dagger.Module
import dagger.Provides
import sammy.mutahi.gicheru.childSpyApp.ui.register.InteractorRegister
import sammy.mutahi.gicheru.childSpyApp.ui.register.InterfaceInteractorRegister
import sammy.mutahi.gicheru.childSpyApp.ui.register.InterfaceViewRegister


@Module
class ActivityModule(var activity: AppCompatActivity) {

    @Provides
    fun provideContext(): Context = activity.applicationContext

    @Provides
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    @PerActivity
    fun provideInterfaceInteractorLogin(interactor: InteractorLogin<InterfaceViewLogin>): InterfaceInteractorLogin<InterfaceViewLogin> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorRegister(interactor: InteractorRegister<InterfaceViewRegister>): InterfaceInteractorRegister<InterfaceViewRegister> = interactor


}