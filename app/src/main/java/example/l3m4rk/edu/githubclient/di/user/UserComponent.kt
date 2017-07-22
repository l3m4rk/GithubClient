package example.l3m4rk.edu.githubclient.di.user

import dagger.Subcomponent
import dagger.android.AndroidInjector
import example.l3m4rk.edu.githubclient.presentation.user.views.UserActivity

@Subcomponent
interface UserComponent : AndroidInjector<UserActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UserActivity>()

}