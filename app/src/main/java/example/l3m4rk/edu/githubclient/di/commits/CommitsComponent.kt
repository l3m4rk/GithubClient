package example.l3m4rk.edu.githubclient.di.commits

import dagger.Subcomponent
import dagger.android.AndroidInjector
import example.l3m4rk.edu.githubclient.presentation.commits.views.CommitsFragment


@Subcomponent(modules = arrayOf(CommitsModule::class))
interface CommitsComponent : AndroidInjector<CommitsFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<CommitsFragment>()
}

