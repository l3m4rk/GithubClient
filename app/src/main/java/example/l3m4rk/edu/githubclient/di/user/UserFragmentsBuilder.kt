package example.l3m4rk.edu.githubclient.di.user

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import example.l3m4rk.edu.githubclient.di.commits.CommitsComponent
import example.l3m4rk.edu.githubclient.di.repos.ReposComponent
import example.l3m4rk.edu.githubclient.presentation.commits.views.CommitsFragment
import example.l3m4rk.edu.githubclient.presentation.repos.views.ReposFragment

@Module
abstract class UserFragmentsBuilder {

    @Binds @IntoMap @FragmentKey(ReposFragment::class)
    abstract fun bindReposFragment(builder: ReposComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds @IntoMap @FragmentKey(CommitsFragment::class)
    abstract fun bindCommitsFragment(builder: CommitsComponent.Builder): AndroidInjector.Factory<out Fragment>

}