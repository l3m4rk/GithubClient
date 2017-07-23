package example.l3m4rk.edu.githubclient.di.repos

import dagger.Subcomponent
import dagger.android.AndroidInjector
import example.l3m4rk.edu.githubclient.presentation.repos.views.ReposFragment

@Subcomponent(modules = arrayOf(ReposModule::class))
interface ReposComponent : AndroidInjector<ReposFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ReposFragment>()

}