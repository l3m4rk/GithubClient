package example.l3m4rk.edu.githubclient.di.repos

import dagger.Module
import dagger.Provides
import example.l3m4rk.edu.githubclient.business.commits.validation.ErrorParser
import example.l3m4rk.edu.githubclient.presentation.repos.presenter.IReposPresenter
import example.l3m4rk.edu.githubclient.presentation.repos.presenter.ReposPresenter
import example.l3m4rk.edu.githubclient.services.GithubService

@Module
class ReposModule {

    @Provides fun provideReposPresenter(githubService: GithubService, errorParser: ErrorParser): IReposPresenter {
        return ReposPresenter(githubService, errorParser)
    }

}