package example.l3m4rk.edu.githubclient.di.user

import dagger.Subcomponent
import dagger.android.AndroidInjector
import example.l3m4rk.edu.githubclient.presentation.user.views.UserActivity

@Subcomponent(modules = arrayOf(UserModule::class, UserFragmentsBuilder::class))
interface UserComponent : AndroidInjector<UserActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UserActivity>()

}