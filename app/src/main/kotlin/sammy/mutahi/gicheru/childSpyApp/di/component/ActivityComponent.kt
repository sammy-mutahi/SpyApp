package sammy.mutahi.gicheru.childSpyApp.di.component

import sammy.mutahi.gicheru.childSpyApp.di.PerActivity
import sammy.mutahi.gicheru.childSpyApp.di.module.ActivityModule
import sammy.mutahi.gicheru.childSpyApp.ui.login.LoginActivity
import sammy.mutahi.gicheru.childSpyApp.ui.main.MainActivity
import sammy.mutahi.gicheru.childSpyApp.ui.social.SocialActivity
import dagger.Component

/**
 * Created by luis rafael on 13/03/18.
 */
@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(socialActivity: SocialActivity)

}